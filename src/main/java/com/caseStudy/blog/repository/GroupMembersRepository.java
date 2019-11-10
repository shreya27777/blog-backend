package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers,Long> {
}
