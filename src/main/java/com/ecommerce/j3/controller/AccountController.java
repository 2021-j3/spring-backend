package com.ecommerce.j3.controller;

import com.ecommerce.j3.domain.entity.AccountDTO;
import com.ecommerce.j3.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@NoArgsConstructor
@AllArgsConstructor
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/auth/register")
    public String getRegister(Model model){
        model.addAttribute("registerForm", new AccountDTO());
        return "register";
    }

    @PostMapping("/auth/register")
    public String postRegister(@ModelAttribute @Valid AccountDTO registerForm, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("registerForm", registerForm);
            model.addAttribute("errors", errors.getAllErrors());
            return "/register";
        }

        accountService.save(registerForm);
        return "redirect:/";
    }

    @GetMapping("/user/info")
    public String getInfo(Model model){
        return "/user/info";
    }
}
