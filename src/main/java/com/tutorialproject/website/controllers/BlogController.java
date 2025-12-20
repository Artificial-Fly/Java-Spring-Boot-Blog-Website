package com.tutorialproject.website.controllers;

import com.tutorialproject.website.models.Post;
import com.tutorialproject.website.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogName(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogPostAdd(Model model){
        return "blog-add";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anounce, @RequestParam String fullText, Model model){
        Post post = new Post(title, anounce, fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogPostDetails(@PathVariable(value = "id") Long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> postOptional = postRepository.findById(id);
        //increase view count by 1
        Post post = postOptional.get();
        post.incViews();
        postRepository.save(post);

        ArrayList<Post> res = new ArrayList<>();
        postOptional.ifPresent(res::add);
        model.addAttribute("post", res);

        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogPostEdit(@PathVariable(value = "id") Long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") Long id, @RequestParam String title, @RequestParam String anounce, @RequestParam String fullText, Model model){
        Post post = postRepository.findById(id)
                .orElseThrow();
        post.setTitle(title);
        post.setAnounce(anounce);
        post.setFullText(fullText);
        postRepository.save(post);
        //postRepository.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostDrop(@PathVariable(value = "id") Long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Post post = postRepository.findById(id)
                .orElseThrow();
        postRepository.delete(post);

        return "redirect:/blog";
    }
}
