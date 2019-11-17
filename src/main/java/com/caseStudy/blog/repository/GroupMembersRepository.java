package com.caseStudy.blog.repository;

import com.caseStudy.blog.model.GroupMembers;
import com.caseStudy.blog.model.UserGroups;
import com.caseStudy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers, Long> {
    List<GroupMembers> findAllByUserGroups(UserGroups userGroups);

    Boolean existsByUserGroupsAndMembers(UserGroups userGroups, Users users);

    Optional<GroupMembers> findByUserGroupsAndMembers(UserGroups userGroups, Users groupMembers);

    List<GroupMembers> findAllByMembers(Users members);
}
