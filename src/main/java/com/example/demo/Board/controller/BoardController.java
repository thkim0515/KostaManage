package com.example.demo.Board.controller;

import com.example.demo.Board.dto.BoardResponseDto;
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
    public BoardResponseDto createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.createBoard(board.getTitle(), board.getContent(), board.getType(), board.getUser().getUserId(), board.getCohortId());
        return boardService.convertToDto(createdBoard);
    }

    @GetMapping("/get/{id}")
    public Optional<BoardResponseDto> getBoardById(@PathVariable Integer id) {
        return boardService.getBoardById(id).map(boardService::convertToDto);
    }

    @GetMapping("/type/{type}")
    public List<BoardResponseDto> getBoardsByType(@PathVariable String type) {
        return boardService.getBoardsByType(type);
    }

    @GetMapping("/all")
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PutMapping("/update/{id}")
    public BoardResponseDto updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        Board updatedBoard = boardService.updateBoard(id, board.getTitle(), board.getContent());
        return boardService.convertToDto(updatedBoard);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
    }

    @PutMapping("/soft-delete/{id}")
    public void softDeleteBoard(@PathVariable Integer id) {
        boardService.softDeleteBoard(id);
    }
}
