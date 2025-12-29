package com.aleksgeek.crud.controller;


import com.aleksgeek.crud.entity.User;
import com.aleksgeek.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping((""))
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 1. Главная страница - список всех пользователей
    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    // 2. Показать форму создание пользователя (GET)
    @GetMapping("/create-form")
    public String showCreateForm(Model model) {
        model.addAttribute("user", null);
        return "users/form";
    }

    // 3. Создать пользователя (POST)
    @PostMapping("/create")
    public String createUserForm(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 RedirectAttributes redirectAttributes) {


        userService.createUser(name, email);
        redirectAttributes.addFlashAttribute("successMessage", "Пользователь успешно создан");
        return "redirect:/";
    }

    // 4. Показать детали пользователя (GET)
    @GetMapping("/user")
    public String getUserDetails(@RequestParam("id") Long id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-details";
    }

    // 5. Показать форму редактирования (GET)
    @GetMapping("/edit-form")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/form";
    }

    // 6. Обновить пользователя (POST)
    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email,
                             RedirectAttributes redirectAttributes) {

        userService.updateUser(id, name, email);
        redirectAttributes.addFlashAttribute
                ("successMessage", "Пользователь успешно обновлен");
        return "redirect:/";
    }

    // 6. Удалить пользователя(POST)

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id,
                             RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Пользователь успешно удален!");
        return "redirect:/";
    }
}
