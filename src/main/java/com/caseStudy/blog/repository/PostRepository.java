package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.Post;
import com.caseStudy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    List<Post> findAllByDescriptionRegexAndIsPrivate(String description, Integer isPrivate);

//    List<Post> findAllByTitleRegexAndIsPrivate(String title, Integer isPrivate);

    List<Post> findAllByCategoryAndIsPrivate(String category, Integer isPrivate);

    List<Post> findAllByAuthor(Users author);

    List<Post> findAllByDateAndIsPrivate(LocalDate date, Integer isPrivate);

    List<Post> findAllByTitleContainingIgnoreCaseAndIsPrivate(String title, Integer isPrivate);

    List<Post> findAllByDescriptionContainingIgnoreCaseAndIsPrivate(String description, Integer isPrivate);

    List<Post> findTop5ByIsPrivateOrderByVisitedDesc(Integer isPrivate);

    List<Post> findAllByAuthorAndIsPrivateOrderByDateDesc(Users author, Integer isPrivate);

    void deleteAllByAuthor(Users users);

    List<Post> findAllByIsPrivate(Integer isPrivate);

}
