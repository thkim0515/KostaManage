package com.example.demo.Board.service;

import com.example.demo.Board.dto.BoardResponseDto;
import com.example.demo.Board.entity.Board;
import com.example.demo.Board.repository.BoardRepository;
import com.example.demo.User.dto.UserDto;
import com.example.demo.User.entity.User;
import com.example.demo.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Board createBoard(String title, String content, String type, Integer userId, Integer cohortId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Board board = Board.builder()
                    .title(title)
                    .content(content)
                    .type(type)
                    .user(user)
                    .cohortId(cohortId)
                    .views(0)
                    .likes(0)
                    .isDeleted(false)
                    .build();
            return boardRepository.save(board);
        } else {
            throw new IllegalArgumentException("작성자를 찾을 수 없습니다.");
        }
    }

    public Optional<Board> getBoardById(Integer id) {
        return boardRepository.findById(id);
    }

    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAllActive().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Board updateBoard(Integer id, String title, String content) {
        Optional<Board> boardOpt = boardRepository.findById(id);
        if (boardOpt.isPresent()) {
            Board board = boardOpt.get();
            board.setTitle(title);
            board.setContent(content);
            return boardRepository.save(board);
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }

    public void deleteBoard(Integer id) {
        Optional<Board> boardOpt = boardRepository.findById(id);
        boardOpt.ifPresent(boardRepository::delete);
    }

    public void softDeleteBoard(Integer id) {
        Optional<Board> boardOpt = boardRepository.findById(id);
        if (boardOpt.isPresent()) {
            Board board = boardOpt.get();
            board.setIsDeleted(true);
            boardRepository.save(board);
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }

    public BoardResponseDto convertToDto(Board board) {
        UserDto userDto = new UserDto(
                board.getUser().getUserId(),
                board.getUser().getName(),
                board.getUser().getEmail(),
                board.getUser().getProfileImg()
        );

        return new BoardResponseDto(
                board.getPostId(),
                board.getTitle(),
                board.getContent(),
                board.getType(),
                userDto,
                board.getCohortId(),
                board.getViews(),
                board.getLikes(),
                board.getCreatedAt().toString(),
                board.getUpdatedAt().toString(),
                board.getIsDeleted()
        );
    }
}
