package com.caseStudy.blog.service;

import com.caseStudy.blog.repository.GroupMembersRepository;
import com.caseStudy.blog.repository.GroupsRepository;
import com.caseStudy.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public Groups createGroup(Principal principal){
//
//        return ;
//    }
//    public List<GroupMembers> addMember(){
//
//    }
}
