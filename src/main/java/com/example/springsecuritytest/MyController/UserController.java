package com.example.springsecuritytest.MyController;

import com.example.springsecuritytest.Entity.User;
import com.example.springsecuritytest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String getPageForAll(){
        return "pageForAllUsers";
    }
    @GetMapping("/authenticated")
    public String authenticMetod(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "pageForAuthenticOnly";
    }
    @PostMapping("/registration")
    public String userRegistration(){
        return "addUser";
    }
}
