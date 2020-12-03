package com.codeup.spring.controllers;

import com.codeup.spring.models.Ad;
import com.codeup.spring.models.Post;
import com.codeup.spring.models.User;
import com.codeup.spring.repository.AdRepository;
import com.codeup.spring.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {

    private final AdRepository adDao;
    private final UserRepository userDao;

    public AdController(AdRepository adDao, UserRepository userDao){
        this.adDao = adDao;
        this.userDao = userDao;
    }

    @GetMapping("/ads")
    public String index(Model viewModel) {
        viewModel.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/search")
    public String search(@RequestParam(name = "term") String term, Model viewModel){
        term = "%"+term+"%";
        List<Ad> dbAds = adDao.findAllByTitleIsLike(term);
        viewModel.addAttribute("ads", dbAds);
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model){
        model.addAttribute("ad", adDao.getOne(id));
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String showCreateForm(){
        return "ads/new";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String createAd(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc
    ){
        User user = userDao.getOne(1L);
        Ad ad = new Ad(title, desc, user, null);
        Ad dbAd = adDao.save(ad);
        return "redirect:/ads/ " + dbAd.getId();
    }

    @GetMapping("/ads/edit")
    public String showEditForm(@PathVariable long id,  Model viewModel){
        viewModel.addAttribute("ad", adDao.getOne(id));
        return "ads/edit";
    }

    @PostMapping("/ads/{id}/edit")
    @ResponseBody
    public String editAd(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc
    ){
        Ad dbAd = adDao.getOne(id);
        dbAd.setTitle(title);
        dbAd.setDescription(desc);
        adDao.save(dbAd);
        return "redirect:/ads/" + dbAd.getId();
    }

    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable long id){
        adDao.deleteById(id);
        return "redirect:/ads";
    }

//    @DeleteMapping("/ads/{id}")
//    public String deleteAd(@PathVariable long id){
//        adDao.deleteById(id);
//        return "redirect:/ads";
//    }
}


