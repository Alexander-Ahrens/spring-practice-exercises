package com.ama.springpracticeexercises.controllers;

import com.ama.springpracticeexercises.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller // Identifies class as a controller without having to create the POST and GET requests
public class PostController {

    //    private final PostRepository postDao;
    private ArrayList<Post> postList;

    public PostController() {
        this.postList = new ArrayList<Post>();

        // adds two posts to the database;
        postList.add(new Post(1, "First post", "new"));
        postList.add(new Post(2, "Second post", "also new"));
    }


    // Defines method that should be invoked by the URI, in this case, "posts";
    // Shows all the posts;
    @GetMapping("/posts")
    @ResponseBody // Is whats displays on the web page: "List of legen... wait for it! DARY posts";
    public String indexPage(Model viewModel) {
        viewModel.addAttribute("posts", postList);
        return "posts/index";
//        return "List of legen... wait for it! DARY posts";
    }


    // Individual post
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postName(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postList.get((int)id - 1));
        return "posts/show";
    }

    // Page that creates an individual post
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
