package com.example.demo.Comment.service;

import com.example.demo.Board.dto.BoardDto;
import com.example.demo.Comment.dto.CommentResponseDto;
import com.example.demo.Comment.entity.Comment;
import com.example.demo.Comment.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Integer boardId, Integer userId, String content, Integer parentId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Comment parent = null;
        if (parentId != null) {
            parent = commentRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글을 찾을 수 없습니다."));
        }

        Comment comment = Comment.builder()
                .board(board)
                .user(user)
                .content(content)
                .parent(parent)
                .build();

        return commentRepository.save(comment);
    }

    public Optional<CommentResponseDto> getCommentById(Integer id) {
        return commentRepository.findById(id).map(this::convertToDto);
    }

    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CommentResponseDto> getCommentsByBoardId(Integer boardId) {
        return commentRepository.findByBoard_PostId(boardId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Comment updateComment(Integer id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }

    public CommentResponseDto convertToDto(Comment comment) {
        BoardDto boardDto = new BoardDto(
                comment.getBoard().getPostId(),
                comment.getBoard().getTitle(),
                comment.getBoard().getContent(),
                comment.getBoard().getType()
        );

        UserDto userDto = new UserDto(
                comment.getUser().getUserId(),
                comment.getUser().getName(),
                comment.getUser().getEmail(),
                comment.getUser().getProfileImg()
        );

        CommentResponseDto parentDto = comment.getParent() != null ? convertToDto(comment.getParent()) : null;

        return new CommentResponseDto(
                comment.getCommentId(),
                boardDto,
                userDto,
                comment.getContent(),
                parentDto
        );
    }
}
