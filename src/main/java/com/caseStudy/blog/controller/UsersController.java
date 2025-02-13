package com.caseStudy.blog.controller;

import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersServiceImp usersServiceImp;

    @Autowired
    public UsersController(UsersServiceImp usersServiceImp) {
        this.usersServiceImp = usersServiceImp;
    }

    @PostMapping(path = "/signup")
    public Boolean addUser(@RequestBody Users users) {
        return usersServiceImp.signUpUser(users);
    }

    @PostMapping(path = "/update")
    @ResponseBody
    public Users updateUser(@RequestBody Users users, Principal principal) {
        return usersServiceImp.update(users, principal);
    }


    @GetMapping(path = "/login")
    @ResponseBody
    public String getRole(Principal principal) {
        return "\"" + usersServiceImp.getRole(principal) + "\"";
    }

    @GetMapping(path = "/logout")
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            request.getSession().invalidate();
        }
        return true;
    }

    @GetMapping(path = "/get-profile", produces = "application/json")
    @ResponseBody
    public Users getProfile(Principal principal) {
        return usersServiceImp.getUser(principal);
    }

    @GetMapping(path = "/getUser/{id}", produces = "application/json")
    @ResponseBody
    public String getUserById(@PathVariable Long id) {
        return usersServiceImp.getUserById(id);
    }

    @GetMapping(path = "/getById/{id}", produces = "application/json")
    @ResponseBody
    public Users getUserObjectById(@PathVariable Long id) {
        return usersServiceImp.getUserObjectById(id);
    }

    @GetMapping("/getByName/{name}")
    public List<Users> getAllByName(@PathVariable String name) {
        return usersServiceImp.getAllByName(name);
    }

}


