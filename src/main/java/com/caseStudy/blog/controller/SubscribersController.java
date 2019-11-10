package com.caseStudy.blog.controller;

import com.caseStudy.blog.model.Subscribers;
import com.caseStudy.blog.service.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subscribers")
@RestController
public class SubscribersController {
    private SubscribersService subscribersService;

    @Autowired
    public SubscribersController(SubscribersService subscribersService) {
        this.subscribersService = subscribersService;
    }

    @GetMapping("/getFollower")
    public List<Subscribers> getSubscribers(Principal principal) {
        return subscribersService.getSubscribers(principal);
    }

    @GetMapping("/getFollowing")
    public List<Subscribers> getSubscription(Principal principal) {
        return subscribersService.getSubscription(principal);
    }

    @PostMapping("/subscribe")
    @ResponseBody
    public List<Subscribers> subscribe(Principal principal, @RequestParam Long id) {
        return subscribersService.subscribe(principal, id);
    }

    @PostMapping("/unSubscribe")
    @ResponseBody
    public List<Subscribers> unSubscribe(Principal principal, @RequestParam Long id) {
        return subscribersService.unSubscribe(principal, id);
    }
}
