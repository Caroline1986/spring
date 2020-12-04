package com.codeup.spring.controllers;

import com.codeup.spring.models.Ad;
import com.codeup.spring.models.Post;
import com.codeup.spring.models.User;
import com.codeup.spring.repository.PostRepository;
import com.codeup.spring.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository adDao, UserRepository userDao) {
        this.postDao = adDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    //Inside the method that shows an individual post, create a new post object and pass it to the view.
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }



    @GetMapping("/posts/create")
    public String createPostForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post postToBeSaved) {
        User userDb = userDao.getOne(1L);
        postToBeSaved.setOwner(userDb);
        Post dbPost = postDao.save(postToBeSaved);
        return "redirect://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }

//     @GetMapping("/posts/{id}/edit")
//    public String viewEditPostForm(@PathVariable long id, Model model) {
//        model.addAttribute("post", postDao.getOne(id));
//        return "posts/edit";
//    }
//
//    @PostMapping("/posts/{id}/edit")
//    public String editPost(
//            @PathVariable long id,
//            @RequestParam String title,
//            @RequestParam String body
//    ){
//       Post dbPost = postDao.getOne(id);
//        dbPost.setTitle(title);
//        dbPost.setBody(body);
//        postDao.save(dbPost);
//        return "redirect:/posts/" + dbPost.getId();
//    }
    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post postToBeUpdated){
        User userDb = userDao.getOne(1L);
        postToBeUpdated.setOwner(userDb);
        postDao.save(postToBeUpdated);
//        return "redirect:/posts";
        Post dbPost = postDao.save(postToBeUpdated);
        return "redirect:/posts/" + dbPost.getId();
    }


    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        System.out.println("Does this run?");
        postDao.deleteById(id);
        return "redirect:/posts";
    }



}
