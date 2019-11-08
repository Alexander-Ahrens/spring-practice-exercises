package com.ama.springpracticeexercises.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Identifies class as a controller without having to create the POST and GET requests
public class PostController {

    // Defines method that should be invoked by the URI, in this case, "posts"
    @GetMapping("/posts")
    @ResponseBody // Is whats displays on the web page: "List of legen... wait for it! DARY posts"
    public String indexPage() {
        return "List of legen... wait for it! DARY posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postName(@PathVariable int id) {
        return "Selling: " + id;
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
