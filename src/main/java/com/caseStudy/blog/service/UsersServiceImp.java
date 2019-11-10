package com.caseStudy.blog.service;

import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.repository.PostRepository;
import com.caseStudy.blog.repository.SubscribersRepository;
import com.caseStudy.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UsersServiceImp {

    private UsersRepository usersRepository;
    private SubscribersRepository subscribersRepository;
    private PostRepository postRepository;

    @Autowired
    public UsersServiceImp(UsersRepository usersRepository, SubscribersRepository subscribersRepository,
                           PostRepository postRepository) {
        this.usersRepository = usersRepository;
        this.subscribersRepository = subscribersRepository;
        this.postRepository = postRepository;
    }

    public String loginUsers(String email, String password) {
        if (usersRepository.existsByEmailAndPassword(email, password)) {
            return "you are logged in";
        }
        return "invalid credentials";
    }

    public Users signUpUser(Users users) {
        if (!usersRepository.existsByEmail(users.getEmail())) {
            users.setRole("user");
            users.setActive(1);
            users.setSubscribers(0L);
            usersRepository.saveAndFlush(users);
            return users;
        }
        return null;
    }

    public String removeAllUsers() {
        usersRepository.deleteAll();
        return "all users removed";
    }

    public List<Users> removeUser(Long id) {
        Users users1 = usersRepository.findByUserId(id).get();
        subscribersRepository.deleteAllBySubscribers(users1);
        postRepository.deleteAllByAuthorId(users1.getUserId());
        usersRepository.delete(users1);
        return usersRepository.findAll();
    }

    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Users getUser(Principal principal) {
        return usersRepository.findByEmail(principal.getName()).get();
    }

    public String getRole(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return users.getRole();
    }

    public Users update(Users users, Principal principal) {
        Users updatedUser = usersRepository.findByEmail(principal.getName()).get();
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        updatedUser.setName(users.getName());
        usersRepository.saveAndFlush(updatedUser);
        return updatedUser;
    }

    public List<Users> updateById(Users users, Long id) {
        Users updatedUser = usersRepository.findById(id).get();
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        updatedUser.setName(users.getName());
        usersRepository.saveAndFlush(updatedUser);
        return usersRepository.findAll();
    }

    public List<Users> toggleActivate(Long id) {
        Users user = usersRepository.findById(id).get();
        if (user.getActive() == 1) {
            user.setActive(0);
        } else {
            user.setActive(1);
        }
        usersRepository.saveAndFlush(user);
        return usersRepository.findAll();
    }
}
