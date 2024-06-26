package com.example.demo.Comment.service;

import com.example.demo.Comment.entity.Comment;
import com.example.demo.Comment.repository.CommentRepository;
import com.example.demo.Board.entity.Board;
import com.example.demo.Board.repository.BoardRepository;
import com.example.demo.User.entity.User;
import com.example.demo.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Board> boardOpt = boardRepository.findById(boardId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (!boardOpt.isPresent()) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }

        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        Board board = boardOpt.get();
        User user = userOpt.get();
        Comment parent = null;
        if (parentId != null) {
            parent = commentRepository.findById(parentId).orElse(null);
        }

        Comment comment = Comment.builder()
                .board(board)
                .user(user)
                .content(content)
                .parent(parent)
                .build();

        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(Integer id, String content) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            comment.setContent(content);
            return commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
    }

    public void deleteComment(Integer id) {
        Optional<Comment> commentOpt = commentRepository.findById(id);
        commentOpt.ifPresent(commentRepository::delete);
    }
}
