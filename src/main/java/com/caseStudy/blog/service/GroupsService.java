package com.caseStudy.blog.service;

import com.caseStudy.blog.model.GroupMembers;
import com.caseStudy.blog.model.UserGroups;
import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.repository.GroupMembersRepository;
import com.caseStudy.blog.repository.GroupsRepository;
import com.caseStudy.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class GroupsService {
    private GroupMembersRepository groupMembersRepository;
    private GroupsRepository groupsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public GroupsService(GroupMembersRepository groupMembersRepository, GroupsRepository groupsRepository,
                         UsersRepository usersRepository) {
        this.groupMembersRepository = groupMembersRepository;
        this.groupsRepository = groupsRepository;
        this.usersRepository = usersRepository;
    }

    public UserGroups createGroup(Principal principal, UserGroups userGroups) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        userGroups.setOwner(users);
        userGroups.setSize(0L);
        groupsRepository.saveAndFlush(userGroups);
        return userGroups;
    }

    public List<GroupMembers> addMember(Long memberId, Long groupId) {
        Users users = usersRepository.findByUserId(memberId).get();
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        if (!groupMembersRepository.existsByUserGroupsAndMembers(userGroups, users)) {
            GroupMembers g = new GroupMembers();
            g.setMembers(users);
            g.setUserGroups(userGroups);
            groupMembersRepository.saveAndFlush(g);
        }
        return groupMembersRepository.findAllByUserGroups(userGroups);
    }

    public List<GroupMembers> removeMember(Long memberId, Long groupId) {
        Users users = usersRepository.findByUserId(memberId).get();
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        if (groupMembersRepository.existsByUserGroupsAndMembers(userGroups, users)) {
            GroupMembers groupMembers =
                    groupMembersRepository.findByUserGroupsAndMembers(userGroups, users).get();
            groupMembersRepository.delete(groupMembers);
        }
        return groupMembersRepository.findAllByUserGroups(userGroups);
    }


}
