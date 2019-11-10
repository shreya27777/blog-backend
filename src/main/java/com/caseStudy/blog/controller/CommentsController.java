package com.caseStudy.blog.controller;

import com.caseStudy.blog.model.Comments;
import com.caseStudy.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentsController {
    private CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/getComments/{id}")
    public List<Comments> getComments(@PathVariable Long id) {
        return commentsService.getComments(id);
    }

    @PostMapping(value = "/addComments/{id}")
    @ResponseBody
    public Comments addComments(@PathVariable Long id, @RequestBody Comments comments,
                                Principal principal) {
        return commentsService.addComments(comments, principal, id);
    }

    @PostMapping(value = "/deleteComments/{id}")
    @ResponseBody
    public List<Comments> deleteComments(@PathVariable Long id) {
        return commentsService.deleteComments(id);
    }

    @PostMapping(value = "/likeComments/{id}")
    @ResponseBody
    public List<Comments> likeComments(@PathVariable Long id) {
        return commentsService.likeComments(id);
    }

    @PostMapping(value = "/dislikeComments/{id}")
    @ResponseBody
    public List<Comments> dislikeComments(@PathVariable Long id) {
        return commentsService.dislikeComments(id);
    }
}
