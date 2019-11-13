package com.caseStudy.blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GroupMembers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserGroups userGroups;
    @ManyToOne
    private Users members;

    public GroupMembers() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserGroups getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(UserGroups userGroups) {
        this.userGroups = userGroups;
    }

    public Users getMembers() {
        return members;
    }

    public void setMembers(Users members) {
        this.members = members;
    }
}
