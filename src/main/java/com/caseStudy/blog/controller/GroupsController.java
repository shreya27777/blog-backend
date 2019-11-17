package com.caseStudy.blog.controller;

import com.caseStudy.blog.model.GroupMembers;
import com.caseStudy.blog.model.Post;
import com.caseStudy.blog.model.UserGroups;
import com.caseStudy.blog.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/groups")
public class GroupsController {
    private GroupsService groupsService;

    @Autowired
    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @PostMapping("/create")
    public List<GroupMembers> createGroup(@RequestBody UserGroups groups, Principal principal) {
        return groupsService.createGroup(principal, groups);
    }

    @GetMapping("/get")
    public List<GroupMembers> getGroups(Principal principal) {
        return groupsService.getGroups(principal);
    }

    @GetMapping("/get-by-id")
    public UserGroups getGroupById(@RequestParam Long id) {
        return groupsService.getGroupById(id);
    }

    @PostMapping("/delete")
    public List<GroupMembers> deleteGroup(@RequestParam Long id, Principal principal) {
        return groupsService.deleteGroup(principal, id);
    }

    @PostMapping("/add-member")
    public List<GroupMembers> addMember(@RequestParam Long groupId, @RequestParam Long memberId) {
        return groupsService.addMember(groupId, memberId);
    }

    @PostMapping("/remove-member")
    public List<GroupMembers> removeMember(@RequestParam Long groupId, @RequestParam Long memberId) {
        return groupsService.removeMember(groupId, memberId);
    }

    @GetMapping("/get-members")
    public List<GroupMembers> getMembers(@RequestParam Long groupId) {
        return groupsService.getMembers(groupId);
    }

    @GetMapping("/is-member")
    public Boolean isMember(@RequestParam Long groupId, @RequestParam Long memberId) {
        return groupsService.isMember(groupId, memberId);
    }

    @GetMapping("get-posts")
    public List<Post> getPosts(@RequestParam Long groupId) {
        return groupsService.getPosts(groupId);
    }

    @GetMapping("is-owner")
    public Boolean isOwner(@RequestParam Long groupId, Principal principal) {
        return groupsService.isOwner(groupId, principal);
    }
}