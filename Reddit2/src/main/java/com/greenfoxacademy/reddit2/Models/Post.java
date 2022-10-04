package com.greenfoxacademy.reddit2.Models;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer score;

    private String url;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @OneToMany(mappedBy = "post")
    List<Vote> listVotes;

    public Post() {
    }

    public Post(String title, String url) {
        this.title = title;
        this.url = url;
        this.listVotes = new ArrayList<>();
        this.score = 0;
    }

    public Post(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.score = 0;
    }

    public Post(String title, String url, User user) {
        this.title = title;
        this.url = url;
        this.user = user;
        this.score = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vote> getListVotes() {
        return listVotes;
    }

    public void setListVotes(List<Vote> listVotes) {
        this.listVotes = listVotes;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
