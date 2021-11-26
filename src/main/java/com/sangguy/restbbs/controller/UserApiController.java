package com.sangguy.restbbs.controller;


import com.sangguy.restbbs.model.Board;
import com.sangguy.restbbs.model.User;
import com.sangguy.restbbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll() {

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
