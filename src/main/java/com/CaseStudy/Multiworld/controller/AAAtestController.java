package com.CaseStudy.Multiworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AAAtestController {
    @GetMapping("test")
    public String test(){
        return "html/test";
    }
}
