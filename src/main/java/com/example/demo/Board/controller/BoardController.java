package com.example.demo.Board.controller;

import com.example.demo.Board.entity.Board;
import com.example.demo.Board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 생성
    @PostMapping("/create")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board.getTitle(), board.getContent(), board.getUser().getId());
    }

    // 특정 게시글 조회
    @GetMapping("/get/{id}")
    public Optional<Board> getBoardById(@PathVariable Integer id) {
        return boardService.getBoardById(id);
    }

    // 모든 게시글 조회
    @GetMapping("/all")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 게시글 수정
    @PutMapping("/update/{id}")
    public Board updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        return boardService.updateBoard(id, board.getTitle(), board.getContent());
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
    }
}
