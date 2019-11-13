package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.Post;
import com.caseStudy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategory(String category);

    List<Post> findAllByAuthor(Users author);

    List<Post> findAllByDate(LocalDate date);

    List<Post> findAllByTitleContainingOrContentContainingIgnoreCase(String title, String title1);

    List<Post> findTop5ByOrderByVisitedDesc();

    void deleteAllByAuthor(Users users);
}
