package com.sangguy.restbbs.controller;

import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.repository.BoardRepository;
import com.sangguy.restbbs.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

//    @Autowired
//    private BoardValidator boardValidator; 사용 안함 별로인거 같음

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(required = false) Long id) {

        if (id == null) {

            model.addAttribute("board", new Board());

        } else {

            Board board = boardRepository.findById(id).orElse(new Board());
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult) {

//        boardValidator.validate(board, bindingResult); 사용 안함 별로인거 같음

        if (bindingResult.hasErrors()) {

            return "board/form";
        }

        boardRepository.save(board);

        return "redirect:/board/list";
    }
}
