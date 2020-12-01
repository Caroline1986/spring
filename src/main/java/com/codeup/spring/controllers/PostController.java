package com.codeup.spring.controllers;

import com.codeup.spring.models.Ad;
import com.codeup.spring.models.Post;
import com.codeup.spring.repository.AdRepository;
import com.codeup.spring.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository adDao) {
        this.postDao = adDao;
    }

    @GetMapping("/posts")
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
//    Inside the method that shows all the posts, create a new array list and add two post objects to it, then pass that list to the view.
//    @GetMapping("/posts")
//    @ResponseBody
//    public String index(Model model) {
//        List<Post> posts = new ArrayList<>();
//        posts.add(new Post("Post 1", "Info 1."));
//        posts.add(new Post("Post 2", "Info 2."));
//        posts.add(new Post("Post 3", "Info 3."));
//
//        model.addAttribute("posts", posts);
//        return "posts/index";
//    }

    //Inside the method that shows an individual post, create a new post object and pass it to the view.
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id, Model model) {
        Post post = new Post("Post 1 " + id, "Information!! " + id + ".");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/show")
    public String postShowbyId(@PathVariable long id){
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "posts/show";
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createAd(
//            @RequestParam(name = "title") String title,
//            @RequestParam(name = "body") String body
//    ){
//        Post post = new Post(title, body);
//        Post dbPost = postDao.save(post);
//        return "create a new Post with the id: " + dbPost.getId();
//    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        // delete post
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post p = postDao.getOne(id);
        p.setTitle(title);
        p.setBody(body);
        postDao.save(p);
        return "redirect:/posts";
    }
}
