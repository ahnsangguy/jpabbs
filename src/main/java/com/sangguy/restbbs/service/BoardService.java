package com.sangguy.restbbs.service;

import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.model.User;
import com.sangguy.restbbs.repository.BoardRepository;
import com.sangguy.restbbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    public Board save(Board board, String username) {

        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
