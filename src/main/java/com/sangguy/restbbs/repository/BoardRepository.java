package com.sangguy.restbbs.repository;

import com.sangguy.restbbs.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
