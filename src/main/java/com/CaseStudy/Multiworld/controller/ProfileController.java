package com.CaseStudy.Multiworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class ProfileController {

    @GetMapping("/profile")
    public String showUserProfile(){
        return "html/profile";
    }

    @GetMapping("/update")
    public String showUpdateUserProfile(){
        return "html/updateUserForm";
    }

    @GetMapping("/profile/{user-id}")
    public ModelAndView showUserProfileForOtherUser(@PathVariable("user-id") Long userId, Model model){
        ModelAndView modelAndView = new ModelAndView("html/viewProfileForOtherUser");
        model.addAttribute("userId",userId);
        return modelAndView;
    }
}
