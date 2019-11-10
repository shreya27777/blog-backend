package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.Subscribers;
import com.caseStudy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribersRepository extends JpaRepository<Subscribers,Long> {
    List<Subscribers> findAllByAuthors(Users users);
    List<Subscribers> findAllBySubscribers(Users users);
    void deleteAllBySubscribers(Users users);
    Optional<Subscribers> findByAuthorsAndSubscribers(Users users, Users author);
}
