package com.codeup.spring.controllers;
import com.codeup.spring.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {
//    Inside the method that shows all the posts, create a new array list and add two post objects to it, then pass that list to the view.
    @GetMapping("/posts")
    @ResponseBody
    public String index() {
       List<Post> postList = new ArrayList<>();
//postList.add(getPost(String title, String body));
        return "/index";
    }

//Inside the method that shows an individual post, create a new post object and pass it to the view.
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPost(@PathVariable long id, Model model) {
        Post post = new Post();
        model.addAttribute("id",id);
        return "/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreateForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String getPostForm() {
        return "create a post";
    }
}
