package com.netcracker.controllers;

import com.netcracker.model.FindUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/home-page")
    public String findUserForm() {
        return "home-page";
    }
}
