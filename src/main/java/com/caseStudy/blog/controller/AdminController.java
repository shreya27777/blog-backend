package com.caseStudy.blog.controller;

import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private UsersServiceImp usersServiceImp;

    @Autowired
    public AdminController(UsersServiceImp usersServiceImp) {
        this.usersServiceImp = usersServiceImp;
    }

    @PostMapping(path = "/addUser")
    public Users addUser(@RequestBody Users users) {
        users.setRole("user");
        users.setActive(1);
        return usersServiceImp.signUpUser(users);
    }

    @PostMapping(path = "/removeUser/{id}")
    public List<Users> removeUser(@PathVariable("id") Long id) {
        return usersServiceImp.removeUser(id);
    }

    @PostMapping(path = "/updateUser/{id}")
    @ResponseBody
    public List<Users> updateUser(@RequestBody Users users, @PathVariable("id") Long id) {
        return usersServiceImp.updateById(users, id);
    }

    @GetMapping(path = "/getAllUsers")
    @ResponseBody
    public List<Users> getAllUsers() {
        return usersServiceImp.getAllUser();
    }

    @PostMapping("/deActivate/{id}")
    public List<Users> toggleActivate(@PathVariable("id") Long id) {
        return usersServiceImp.toggleActivate(id);
    }
}
