package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersDetailService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UsersDetailService userService;

    @Autowired
    public UserController(UsersDetailService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userInfo(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("aloneUser", user);
        model.addAttribute("aloneUser2", user);

        return "user";
    }

}

