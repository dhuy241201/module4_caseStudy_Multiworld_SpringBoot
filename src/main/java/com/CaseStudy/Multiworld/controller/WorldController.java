package com.CaseStudy.Multiworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("world")
public class WorldController {
    @GetMapping("/create")
    public String showCreateForm(){
        return "html/createWorldForm";
    }


    @GetMapping("/show")
    public String showWorld(){
        return "html/showWorldForUser";
    }

    @GetMapping("/show/view/{id}")
    public ModelAndView viewWorld(@PathVariable Long id, Model model){
        ModelAndView modelAndView = new ModelAndView("html/viewWorldForUser");
        model.addAttribute("id",id);
        return modelAndView;
    }

    @GetMapping("/show/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id, Model model){
        ModelAndView modelAndView = new ModelAndView("html/updateWorldForm");
        model.addAttribute("id",id);
        return modelAndView;
    }

}
