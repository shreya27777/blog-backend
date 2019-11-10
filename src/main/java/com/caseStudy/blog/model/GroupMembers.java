package com.caseStudy.blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GroupMembers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Groups groups;
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

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Users getMembers() {
        return members;
    }

    public void setMembers(Users members) {
        this.members = members;
    }
}
