package com.caseStudy.blog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Post implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(nullable = false)
    private String content;
   @Column(nullable = false)
    private String description;
   @Column(nullable = false)
    private String title;
   @Column(nullable = false)
    private Long authorId;
   @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Integer isPrivate;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Long visited;
    @Column(nullable = false)
    private Long likes;
    @Column(nullable = false)
    private Long disLikes;
    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getVisited() {
        return visited;
    }

    public void setVisited(Long visited) {
        this.visited = visited;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(Long disLikes) {
        this.disLikes = disLikes;
    }
}
