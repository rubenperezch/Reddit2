package com.greenfoxacademy.reddit2.Models;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean score;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    public Vote() {
    }

    public Vote(Boolean score, Post post) {
        this.score = score;
        this.post = post;
    }

    public Vote(Long id, Boolean score) {
        this.id = id;
        this.score = score;
    }

    public Vote(Long id, Boolean score, Post post) {
        this.id = id;
        this.score = score;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getScore() {
        return score;
    }

    public void setScore(Boolean score) {
        this.score = score;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
