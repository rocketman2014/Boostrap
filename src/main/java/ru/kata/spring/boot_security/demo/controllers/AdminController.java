package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UsersDetailService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UsersDetailService userService;
    private final RoleRepository roleRepository;


    @Autowired
    public AdminController(UsersDetailService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;

    }

    @GetMapping
    public String allUser(Principal principal, Model model) {
        //страничка юзера
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("thisUser", user);

        //таблица всех юзеров
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);

        //для ролей
        Collection<Role> list = new ArrayList<>();
        list.add(roleRepository.getById(1L));
        list.add(roleRepository.getById(2L));
        model.addAttribute("rolesList", list);

        //добавление нового юзера
        User user1 = new User ();
        model.addAttribute("newUser", user1);
        return "all_user";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delById(id);
        return "redirect:/admin";
    }

    @PostMapping("/updateInfo")
    public String updateUser(@ModelAttribute("oneUser") @Valid User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
