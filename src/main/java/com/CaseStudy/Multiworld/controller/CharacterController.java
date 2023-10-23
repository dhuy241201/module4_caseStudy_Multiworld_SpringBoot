package com.CaseStudy.Multiworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("character")
public class CharacterController {

    @GetMapping("/show/{worldId}")
    public ModelAndView showAllCharacterForUser(@PathVariable Long worldId, Model model){
        ModelAndView modelAndView = new ModelAndView("html/showCharacterForUser");
        model.addAttribute("worldID",worldId);
        return modelAndView;
    }

    @GetMapping("/show/{worldId}/view/{characterId}")
    public ModelAndView viewCharacterForUser(@PathVariable Long characterId,@PathVariable Long worldId, Model model){
        ModelAndView modelAndView = new ModelAndView("html/viewCharacterForUser");
        model.addAttribute("characterId",characterId);
        model.addAttribute("worldId",worldId);
        return modelAndView;
    }

    @GetMapping("/show/{worldId}/create")
    public ModelAndView showCreateCharacterForm(@PathVariable Long worldId, Model model){
        ModelAndView modelAndView = new ModelAndView("html/createCharacterForm");
        model.addAttribute("worldID",worldId);
        return modelAndView;
    }

    @GetMapping("/show/{worldId}/update/{characterId}")
    public ModelAndView showUpdateCharacterForm(@PathVariable Long characterId,@PathVariable Long worldId, Model model){
        ModelAndView modelAndView = new ModelAndView("html/updateCharacterForm");
        model.addAttribute("characterId",characterId);
        model.addAttribute("worldId",worldId);
        return modelAndView;
    }

}
