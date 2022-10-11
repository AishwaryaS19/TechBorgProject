package com.emb.techborg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = {"/user/dashboard"})
    public String userHome(){
        return "user/dashboard";
    }
}