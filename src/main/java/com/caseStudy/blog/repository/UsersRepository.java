package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUserId(Long userId);
}
