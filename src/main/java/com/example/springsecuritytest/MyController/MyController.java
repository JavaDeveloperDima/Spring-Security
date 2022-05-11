package com.example.springsecuritytest.MyController;

import com.example.springsecuritytest.Entity.Role;
import com.example.springsecuritytest.Entity.User;
import com.example.springsecuritytest.Service.UserServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class MyController {
    @Autowired
    private UserServiceClass userServiceClass;
    @GetMapping()
    public String getAll(Model model){
        List<User> list = userServiceClass.getAll();
        model.addAttribute("userList",list);
        return "showAllUsers";
    }
    @GetMapping("/{id}")
    public String getFromId(@PathVariable int id, Model model){
        model.addAttribute("user",userServiceClass.findUserById(id));
        return "showInfo";

    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id,Model model){
        User user = userServiceClass.findUserById(id);
        model.addAttribute("user" +
                "",user);
        return "edit";
    }
    @GetMapping("/new")
    public String getFormAddNewUSer(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "addUser";
    }
    @PostMapping("/delete/{id}")
    public String deleteEmpl(@PathVariable int id){
       if(userServiceClass.removeById(id)){
           return "redirect:/api";
       }else {
           return "User with id "+ id +" dose not exist";
       }
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("user")User user){
        userServiceClass.saveUser(user);
        return "redirect:/api";
    }
    @PostMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("user")User user){
        if(userServiceClass.saveUser(user)){
            return "redirect:/api";
        }else {
            return "User with name " + user.getUsername() + " have already exist";
        }
    }
}
