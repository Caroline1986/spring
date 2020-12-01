package com.codeup.spring.controllers;

import com.codeup.spring.models.Ad;
import com.codeup.spring.models.AdRepository;
import com.codeup.spring.models.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class AdController {

    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model vModel) {
        vModel.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }
//
//    @GetMapping("/ads")
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

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String show(@PathVariable long id, Model model) {
        Post post = new Post ("Post 1 " + id, "Information!! " + id +"." );

        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/ads/create")
    @ResponseBody
    public String showCreateForm() {
        return "ads/new";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String createAd(@RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String desc
                           ) {
        Ad ad = new Ad(title, desc);
        Ad dbAd = adDao.save(ad);
        return "create an ad with the id: " + dbAd.getId();
    }
}


