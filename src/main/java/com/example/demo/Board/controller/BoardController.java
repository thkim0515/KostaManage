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

    @PostMapping("/create")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board.getTitle(), board.getContent(), board.getType(), board.getUser().getUserId(), board.getCohortId());
    }

    @GetMapping("/get/{id}")
    public Optional<Board> getBoardById(@PathVariable Integer id) {
        return boardService.getBoardById(id);
    }

    @GetMapping("/all")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PutMapping("/update/{id}")
    public Board updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        return boardService.updateBoard(id, board.getTitle(), board.getContent());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
    }
}
