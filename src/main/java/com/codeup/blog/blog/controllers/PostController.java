package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.services.EmailService;
import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    @Autowired
    EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model viewModel){
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model vModel){
        vModel.addAttribute("post" , new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post postToBeCreated,
                         @RequestParam(name = "timeout") String timeout){
        System.out.println("timeout = " + timeout);
        postToBeCreated.setUser(userDao.getOne(1L));
        Post savedPost = postDao.save(postToBeCreated);
        emailService.prepareAndSend(savedPost, "Post created", "A Post has been created, with the id of " + savedPost.getId());
        return "redirect:/posts/" + savedPost.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String description) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setBody(description);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    // Repository Testing for JPA Lecture

    @GetMapping("/posts/search")
    @ResponseBody
    public Post search() {
        return postDao.findByTitle("Gazella thompsonii");
    }

    @ResponseBody
    @GetMapping("/list-posts")
    public List<Post> returnAds() {
        return postDao.findAll();
    }

    @ResponseBody
    @GetMapping("/posts/length")
    public List<String> returnAdsByLength() {
        return postDao.getPostsOfCertainTitleLength();
    }

    @ResponseBody
    @GetMapping("/ads/length/native")
    public List<String> returnAdsByLengthNative() {
        return postDao.getPostsOfCertainTitleLengthNative();
    }

}