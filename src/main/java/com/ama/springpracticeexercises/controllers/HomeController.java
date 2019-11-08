package com.ama.springpracticeexercises.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home/{websiteName}")
    @ResponseBody
    public String homePage(@PathVariable String websiteName) {
        return "This is the landing page for " + websiteName + "!";
    }



}
