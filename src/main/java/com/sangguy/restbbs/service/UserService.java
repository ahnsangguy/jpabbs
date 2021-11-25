package com.sangguy.restbbs.service;

import com.sangguy.restbbs.model.Role;
import com.sangguy.restbbs.model.User;
import com.sangguy.restbbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {

        String encodePw = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePw);
        user.setEnabled(true);

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        return userRepository.save(user);
    }
}
