package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.UserGroups;
import com.caseStudy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<UserGroups,Long> {
    List<UserGroups> findAllByOwner(Users users);
}
