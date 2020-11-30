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
    public String index(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post 1", "Info 1."));
        posts.add(new Post("Post 2", "Info 2."));
        posts.add(new Post("Post 3", "Info 3."));

        model.addAttribute("posts", posts);
        return "posts/index";
    }

//Inside the method that shows an individual post, create a new post object and pass it to the view.
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id, Model model) {
        Post post = new Post ("Post 1 " + id, "Information!! " + id +"." );

        model.addAttribute("post", post);
        return "posts/show";
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
