package com.sangguy.restbbs.controller;

import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.repository.BoardRepository;
import com.sangguy.restbbs.service.BoardService;
import com.sangguy.restbbs.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private BoardService boardService;

//    @Autowired
//    private BoardValidator boardValidator; 사용 안함 별로인거 같음

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {

//        List<Board> boards = boardRepository.findAll();
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        model.addAttribute("boards", boards);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 3);
        int endPage = Math.min(boards.getTotalPages() == 0 ? 1 : boards.getTotalPages(), boards.getPageable().getPageNumber() + 5);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {

//        boardValidator.validate(board, bindingResult); 사용 안함 별로인거 같음

        if (bindingResult.hasErrors()) {

            return "board/form";
        }

        String username = authentication.getName();
        boardService.save(board, username);

        return "redirect:/board/list";
    }
}
