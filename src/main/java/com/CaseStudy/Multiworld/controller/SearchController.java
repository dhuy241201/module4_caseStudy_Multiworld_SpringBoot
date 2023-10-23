package com.CaseStudy.Multiworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("search")
public class SearchController {
    @GetMapping("/world")
    public ModelAndView searchWorld(@RequestParam String keyword, Model model){
        ModelAndView modelAndView = new ModelAndView("html/search");
        model.addAttribute("keyword",keyword);
        model.addAttribute("object","world");
        return modelAndView;
    }
    @GetMapping("/character")
    public ModelAndView searchCharacter(@RequestParam String keyword, Model model){
        ModelAndView modelAndView = new ModelAndView("html/search");
        model.addAttribute("keyword",keyword);
        model.addAttribute("object","character");
        return modelAndView;
    }
    @GetMapping("/user")
    public ModelAndView searchUser(@RequestParam String keyword, Model model){
        ModelAndView modelAndView = new ModelAndView("html/search");
        model.addAttribute("keyword",keyword);
        model.addAttribute("object","user");
        return modelAndView;
    }
}
