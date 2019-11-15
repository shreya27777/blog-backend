package com.caseStudy.blog.controller;

import com.caseStudy.blog.model.Post;
import com.caseStudy.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/getAll")
    @ResponseBody
    public List<Post> getPosts() {
        return postService.getAllPost();
    }

    @GetMapping(value = "/getByCategory/{category}")
    @ResponseBody
    public List<Post> getPostsByCategory(@PathVariable String category) {
        return postService.getPostByCategory(category);
    }

    @GetMapping(value = "/getById/{id}")
    @ResponseBody
    public Post getById(@PathVariable Long id) {
        return postService.getPostByUser(id);
    }

    @GetMapping(value = "/getByTitle/{title}")
    @ResponseBody
    public List<Post> getByTitle(@PathVariable String title) {
        return postService.getPostByTitle(title);
    }

    @GetMapping(value = "/getByDescription/{description}")
    @ResponseBody
    public List<Post> getByDescription(@PathVariable String description) {
        return postService.getPostByDescription(description);
    }

    @GetMapping(value = "/getMyPosts")
    @ResponseBody
    public List<Post> getMyPosts(Principal principal) {
        return postService.getMyPost(principal);
    }

    @GetMapping(value = "/getByDate/{year}/{month}/{day}")
    @ResponseBody
    public List<Post> getByDate(@PathVariable int year, @PathVariable int month,
                                @PathVariable int day) {
        return postService.getPostByDate(year, month, day);
    }

    @PostMapping(value = "/addPost")
    @ResponseBody
    public Post addPost(@RequestBody Post post, Principal principal) {
        return postService.addPost(post, principal);
    }

    @PostMapping(value = "/deletePost")
    @ResponseBody
    public List<Post> deletePost(@RequestBody Long id) {
        return postService.deletePost(id);
    }

    @PostMapping(path = "/updatePost/{id}")
    @ResponseBody
    public Post updateUser(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }


    @PostMapping(path = "/viewPost/{id}")
    @ResponseBody
    public void viewPost(@PathVariable Long id) {
        postService.viewPost(id);
    }

    @GetMapping(value = "/getPopular")
    @ResponseBody
    public List<Post> getPopular() {
        return postService.popular();
    }

    @GetMapping(value = "/getAllPostofUser/{id}")
    @ResponseBody
    public List<Post> getAllPostofUser(@PathVariable Long id) {
        return postService.getAllPostofUser(id);
    }

    @GetMapping(path = "/getByAuthorName/{name}")
    public List<Post> findAllByAuthorName(@PathVariable String name) {
        return postService.findAllByAuthorName(name);
    }
}
