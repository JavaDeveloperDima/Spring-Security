package com.example.springsecuritytest.MyController;

import com.example.springsecuritytest.Entity.User;
import com.example.springsecuritytest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private UserService userService;
    @GetMapping("/api")
    public String getAll(Model model){
        List<User> list = userService.getAll();
        model.addAttribute("userList",list);
        return "showAllUsers";
    }
    @GetMapping("/api/{id}")
    public String getFromId(@PathVariable int id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "showUserInfo";

    }
    @GetMapping("/api/{id}/edit")
    public String edit(@PathVariable("id")int id,Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user" +
                "",user);
        return "updateUsersPage";
    }
    @GetMapping("/new")
    public String getFormAddNewUSer(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "addUser";
    }
    @PostMapping("/api/delete/{id}")
    public String deleteEmpl(@PathVariable int id){
       if(userService.removeById(id)){
           return "redirect:/api";
       }else {
           return "User with id "+ id +" dose not exist";
       }
    }
    @PostMapping("/api/update/{id}")
    public String update(@ModelAttribute("user")User user){
        userService.saveUser(user);
        return "redirect:/api";
    }
    @PostMapping("/api/addNewUser")
    public String addNewUser(@ModelAttribute("user")User user){
        if(userService.saveUser(user)){
            return "redirect:/api";
        }else {
            return "User with name " + user.getUsername() + " have already exist";
        }
    }
}
