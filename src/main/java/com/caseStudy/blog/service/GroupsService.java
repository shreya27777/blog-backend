package com.caseStudy.blog.service;

import com.caseStudy.blog.model.GroupMembers;
import com.caseStudy.blog.model.Post;
import com.caseStudy.blog.model.UserGroups;
import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.repository.GroupMembersRepository;
import com.caseStudy.blog.repository.GroupsRepository;
import com.caseStudy.blog.repository.PostRepository;
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
    private PostRepository postRepository;

    @Autowired
    public GroupsService(GroupMembersRepository groupMembersRepository, GroupsRepository groupsRepository,
                         UsersRepository usersRepository, PostRepository postRepository) {
        this.groupMembersRepository = groupMembersRepository;
        this.groupsRepository = groupsRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    public List<GroupMembers> createGroup(Principal principal, UserGroups userGroups) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        userGroups.setOwner(users);
        userGroups.setSize(0L);
        groupsRepository.saveAndFlush(userGroups);
        addMember(userGroups.getId(), users.getUserId());
        return groupMembersRepository.findAllByMembers(users);
    }

    public List<GroupMembers> addMember(Long groupId, Long memberId) {
        Users users = usersRepository.findByUserId(memberId).get();
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        if (!groupMembersRepository.existsByUserGroupsAndMembers(userGroups, users)) {
            GroupMembers g = new GroupMembers();
            g.setMembers(users);
            g.setUserGroups(userGroups);
            userGroups.setSize(userGroups.getSize() + 1);
            groupMembersRepository.saveAndFlush(g);
        }
        return groupMembersRepository.findAllByUserGroups(userGroups);
    }

    public List<GroupMembers> removeMember(Long groupId, Long memberId) {
        Users users = usersRepository.findByUserId(memberId).get();
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        if (groupMembersRepository.existsByUserGroupsAndMembers(userGroups, users)) {
            GroupMembers groupMembers =
                    groupMembersRepository.findByUserGroupsAndMembers(userGroups, users).get();
            groupMembersRepository.delete(groupMembers);
            userGroups.setSize(userGroups.getSize() - 1);
        }
        return groupMembersRepository.findAllByUserGroups(userGroups);
    }

    public List<GroupMembers> getGroups(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return groupMembersRepository.findAllByMembers(users);
    }

    public List<GroupMembers> deleteGroup(Principal principal, Long id) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        UserGroups groups = groupsRepository.findById(id).get();
        for (GroupMembers members : groupMembersRepository.findAllByUserGroups(groups)) {
            groupMembersRepository.delete(members);
        }
        groupsRepository.delete(groups);
        return groupMembersRepository.findAllByMembers(users);
    }

    public List<GroupMembers> getMembers(Long id) {
        UserGroups userGroups = groupsRepository.findById(id).get();
        return groupMembersRepository.findAllByUserGroups(userGroups);
    }

    public List<Post> getPosts(Long groupId) {
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        Users users = userGroups.getOwner();

        return postRepository.findAllByAuthorAndIsPrivateOrderByDateDesc(users, 1);
    }

    public UserGroups getGroupById(Long id) {
        return groupsRepository.findById(id).get();
    }

    public Boolean isMember(Long groupId, Long memberId) {
        Users users = usersRepository.findById(memberId).get();
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        return groupMembersRepository.existsByUserGroupsAndMembers(userGroups, users);
    }

    public Boolean isOwner(Long groupId, Principal principal) {
        UserGroups userGroups = groupsRepository.findById(groupId).get();
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return userGroups.getOwner().equals(users);
    }
}
