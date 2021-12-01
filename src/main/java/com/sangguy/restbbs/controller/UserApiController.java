package com.sangguy.restbbs.controller;


import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.model.User;
import com.sangguy.restbbs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll() {

//        List<User> users = userRepository.findAll();
//        log.debug("get(0).getBoards().size() 호출 전");
//        log.debug("users.get(0).getBoards().size() : {}", users.get(0).getBoards().size());
//        log.debug("get(0).getBoards().size() 호출 후");
//        return users;
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getOne(@PathVariable Long id) {

        return userRepository.findById(id).orElseThrow(() -> new NullPointerException());
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
//                    user.setTitle(newUser.getTitle());
//                    user.setContent(newUser.getContent());

//                    user.setBoards(newUser.getBoards());

                    user.getBoards().clear();;
                    user.getBoards().addAll(newUser.getBoards());

                    for (Board board : user.getBoards()) {

                        board.setUser(user);
                    }

                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);
    }
}
