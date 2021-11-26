package com.sangguy.restbbs.repository;

import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
