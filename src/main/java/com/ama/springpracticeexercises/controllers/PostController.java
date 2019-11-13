package com.ama.springpracticeexercises.controllers;

import com.ama.springpracticeexercises.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.String.*

import java.util.ArrayList;
import java.util.List;

@Controller // Identifies class as a controller without having to create the POST and GET requests
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


    // Defines method that should be invoked by the URI, in this case, "posts";
    // Shows all the posts;
    @GetMapping("/posts")
    //@ResponseBody // Is whats displays on the web page;
    public String indexPage(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
//        return "List of legen... wait for it! DARY posts";
    }


    // Individual post
    @GetMapping("/posts/{id}")
   // @ResponseBody
    public String postName(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(1));
        return "posts/show";
    }

    // Page that creates an individual post
    @GetMapping("/posts/create")
   // @ResponseBody
    public String createPost() {
        return "posts/create";
    }


    @PostMapping("posts/create")
    // @ResponseBody
    public String post(@RequestParam String title, @RequestParam String body) {
        Post post = postDao.save(new Post(title, body));
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts{id}/edit")
     public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setBody(body);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/search")
    @ResponseBody
    public Post search() {
        return postDao.findByTitle("");
    }

    @GetMapping("/list-posts")
    @ResponseBody
    public List<Post> returnPosts() {
        return postDao.findAll();
    }

    @GetMapping("/posts/length")
    @ResponseBody
    public List<String> returnAdsByLength() {
        return postDao.getPostsOfCertainTitleLength();
    }

    @GetMapping("/posts/length/native")
    @ResponseBody
    public List<String> returnAdsByLengthNative() {
        return postDao.getPostsOfCertainTitleLengthNative();
    }
}
