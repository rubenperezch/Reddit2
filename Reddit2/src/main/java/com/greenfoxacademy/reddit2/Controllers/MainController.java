package com.greenfoxacademy.reddit2.Controllers;

import com.greenfoxacademy.reddit2.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    UserServiceImpl userServiceImpl;

    PostServiceImpl postServiceImpl;

    VoteServiceImpl voteServiceImpl;

    @Autowired
    public MainController(UserServiceImpl userServiceImpl, PostServiceImpl postServiceImpl, VoteServiceImpl voteServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.postServiceImpl = postServiceImpl;
        this.voteServiceImpl = voteServiceImpl;
    }

    @GetMapping("/")
    public String renderHome() {
        return "index";
    }

    @PostMapping("/login")
    public String returnLoginPage(@RequestParam String username, @RequestParam String password) {
        if(userServiceImpl.loginUser(username, password) == true) {
            return "redirect:/user/"+userServiceImpl.findFirstByUsername(username).getId();
        }
        return "fail";
    }

    @GetMapping("/register")
    public String renderRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        if(userServiceImpl.existsUserByUsername(username) == true) {
            return "fail";
        } else {
            userServiceImpl.addUser(username, password);
            return "success";
        }
    }

    @GetMapping("/user/{id}")
    public String renderUserPage(Model model, @PathVariable (required = false) Long id) {
        model.addAttribute("user",userServiceImpl.findFirstById(id));
        model.addAttribute("posts", postServiceImpl.findAll());
        return "user";
    }

    @PostMapping("/user/{id}")
    public String post(@PathVariable (required = false) Long id, @RequestParam String title, @RequestParam String url) {
        postServiceImpl.post(title, url, id);

        return "redirect:/user/"+id;
    }

    @GetMapping("/plus/{id}/{userId}")
    public String plus(@PathVariable (required = false) Long id, @PathVariable (required = false) Long userId) {
        voteServiceImpl.votePlus(id);
        return "redirect:/user/"+userId;
    }

    @GetMapping("/minus/{id}/{userId}")
    public String minus(@PathVariable (required = false) Long id, @PathVariable (required = false) Long userId) {
        voteServiceImpl.voteMinus(id);
        return "redirect:/user/"+userId;
    }
}
