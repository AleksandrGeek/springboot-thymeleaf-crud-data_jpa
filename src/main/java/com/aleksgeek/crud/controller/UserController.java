package com.aleksgeek.crud.controller;


import com.aleksgeek.crud.entity.User;
import com.aleksgeek.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(("/users"))
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 1. Главная страница - список всех пользователей
    @GetMapping({"", "/"})  // Обрабатывает и /users и /users/
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    // 2. Показать форму создание пользователя (GET)
    @GetMapping("/create-form")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    // 3. Создать пользователя (POST)
    @PostMapping("/create")
    public String createUserForm(@ModelAttribute User user,
                                 RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "Пользователь успешно создан");
        return "redirect:/users/";
    }

    // 4. Показать детали пользователя (GET)
    @GetMapping("/details")
    public String getUserDetails(@RequestParam("id") Long id, Model model) {

        model.addAttribute("user", userService.getUserById(id));
        return "users/details";
    }

    // 5. Показать форму редактирования (GET)
    @GetMapping("/edit-form")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/form";
    }

    // 6. Обновить пользователя (POST)
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,
                             RedirectAttributes redirectAttributes) {

        userService.updateUser(user);
        redirectAttributes.addFlashAttribute
                ("successMessage", "Пользователь успешно обновлен");
        return "redirect:/users/";
    }

    // 7. Удалить пользователя(POST)
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id,
                             RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Пользователь успешно удален!");
        return "redirect:/users";
    }
}
