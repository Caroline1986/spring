package com.codeup.spring.controllers;

import com.codeup.spring.models.Post;
import com.codeup.spring.models.User;
import com.codeup.spring.repository.PostRepository;
import com.codeup.spring.repository.UserRepository;
import com.codeup.spring.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository adDao, UserRepository userDao, EmailService emailService) {
        this.postDao = adDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String createPost(@ModelAttribute Post postToBeSaved, @RequestParam(name = "file") MultipartFile uploadedFile,
                             Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);

        User userDb = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeSaved.setOwner(userDb);
        Post dbPost = postDao.save(postToBeSaved);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        emailService.prepareAndSend(dbPost, "Post has been created", "You can find it with the id of " + dbPost.getId());
        return "redirect:/posts/" + dbPost.getId();
//        return "redirect://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }

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

    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "fileupload";
    }

    @PostMapping("/fileupload")
    public String saveFile(
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return "redirect:/posts";
    }

}
