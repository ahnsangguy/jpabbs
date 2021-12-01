package com.sangguy.restbbs.controller;


import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/boards")
    public List<Board> getAll(@RequestParam(required = false) String title) {

        if (title == null) {

            return boardRepository.findAll();

        } else {

            return boardRepository.findByTitle(title);
        }
    }

    @PostMapping("/boards")
    public Board saveBoard(@RequestBody Board board) {

        return boardRepository.save(board);
    }

    @GetMapping("/boards/{id}")
    public Board getOne(@PathVariable Long id) {

        return boardRepository.findById(id).orElseThrow(() -> new NullPointerException());
    }

    @PutMapping("/boards/{id}")
    public Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());

                    return boardRepository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardRepository.save(newBoard);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id) {

        boardRepository.deleteById(id);
    }
}
