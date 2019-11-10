package com.caseStudy.blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Subscribers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users subscribers;
    @ManyToOne
    private Users authors;

    public Subscribers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Users subscribers) {
        this.subscribers = subscribers;
    }

    public Users getAuthors() {
        return authors;
    }

    public void setAuthors(Users authors) {
        this.authors = authors;
    }
}
