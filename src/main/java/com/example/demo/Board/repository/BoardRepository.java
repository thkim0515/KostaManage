package com.example.demo.Board.repository;

import com.example.demo.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b WHERE b.type = :type AND b.isDeleted = false")
    List<Board> findByTypeAndIsDeletedFalse(String type);

    @Query("SELECT b FROM Board b WHERE b.isDeleted = false")
    List<Board> findAllActive();
}
