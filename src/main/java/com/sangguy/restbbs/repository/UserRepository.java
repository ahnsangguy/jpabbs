package com.sangguy.restbbs.repository;

import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 각각 테이블 별로 Query 진행하던 것을 join 사용하도록 변경
    @EntityGraph(attributePaths = { "boards" })
    List<User> findAll();

    User findByUsername(String username);
}
