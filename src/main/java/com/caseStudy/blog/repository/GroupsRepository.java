package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<Groups,Long> {
}
