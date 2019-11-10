package com.caseStudy.blog.service;

import com.caseStudy.blog.model.Subscribers;
import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.repository.SubscribersRepository;
import com.caseStudy.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class SubscribersService {
    private SubscribersRepository subscribersRepository;
    private UsersRepository usersRepository;

    @Autowired
    public SubscribersService(SubscribersRepository subscribersRepository,
                              UsersRepository usersRepository) {
        this.subscribersRepository = subscribersRepository;
        this.usersRepository = usersRepository;
    }

    public List<Subscribers> getSubscribers(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return subscribersRepository.findAllByAuthors(users);
    }

    public List<Subscribers> getSubscription(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return subscribersRepository.findAllBySubscribers(users);
    }

    public List<Subscribers> subscribe(Principal principal, Long id) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        Users author = usersRepository.findByUserId(id).get();
        if (!users.equals(author) && !subscribersRepository.findByAuthorsAndSubscribers
                (author, users).isPresent()) {
            Subscribers s = new Subscribers();
            s.setAuthors(author);
            s.setSubscribers(users);
            author.setSubscribers(author.getSubscribers()+1);
            subscribersRepository.saveAndFlush(s);
            usersRepository.saveAndFlush(author);
        }
        return subscribersRepository.findAllBySubscribers(users);
    }

    public List<Subscribers> unSubscribe(Principal principal, Long id) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        Users author = usersRepository.findByUserId(id).get();
        Optional<Subscribers> subscribers=subscribersRepository.findByAuthorsAndSubscribers
                (author,users);
        if (!users.equals(author) && subscribers.isPresent()) {
           subscribersRepository.delete(subscribers.get());
            author.setSubscribers(author.getSubscribers()-1);
            usersRepository.saveAndFlush(author);
        }

        return subscribersRepository.findAllBySubscribers(users);
    }
}
