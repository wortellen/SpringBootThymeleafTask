package com.netcracker.controllers;

import com.netcracker.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class NewFormController {

    @GetMapping("/new-form")
    public String newForm(Model model) {
        model.addAttribute("user", new User());
        return "new-form";
    }

    @PostMapping("/new-form")
    public String formSubmit(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-form";
        }else {
            try (FileWriter writer = new FileWriter("users_data.txt", true)) {
                writer.write(user.toString());
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
            return "form-result";
        }
    }
}
