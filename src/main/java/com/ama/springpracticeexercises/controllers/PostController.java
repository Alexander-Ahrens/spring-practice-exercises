package com.ama.springpracticeexercises.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String indexPage() {
        return "List of legen... wait for it! DARY posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postName() {
        return "Selling: Superman powers";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Enter the name and description of your post.";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String post(@RequestParam String title, @RequestParam String body) {
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);
        return "Create post";
    }

}
