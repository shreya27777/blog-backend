package com.caseStudy.blog.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Groups implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Users owner;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long size;
    private static final Integer MAX=256;


    public Groups() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public static Integer getMAX() {
        return MAX;
    }
}
