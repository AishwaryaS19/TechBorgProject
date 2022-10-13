package com.emb.techborg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.emb.techborg.model.User;
import com.emb.techborg.service.UserService;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {
	
    @Autowired
    UserService userService;
	
    @GetMapping(value = {"/login"})
    public String login(){
        return "auth/login";
    }

    @GetMapping(value = {"/register"})
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping(value = {"/register"})
    public String registerUser(@Valid @ModelAttribute("user") User user,
    		BindingResult bindingResult, Model model){
    	try {
	        if(bindingResult.hasErrors()){
	        	model.addAttribute("bindingResult", bindingResult);
	            model.addAttribute("successMessage", "Fill in all details!");
	            return "auth/register";
	        }
	        List<Object> userPresentObj = userService.isUserPresent(user);
	        if((Boolean) userPresentObj.get(0)){
	            model.addAttribute("successMessage", userPresentObj.get(1));
	            return "auth/register";
	        }
	        userService.saveUser(user);
	        model.addAttribute("successMessage", "User registered successfully!");
	        return "auth/register";
    	}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("successMessage", "Server error! Please try again later!");
            }
		return "auth/register";
    	
    }
}