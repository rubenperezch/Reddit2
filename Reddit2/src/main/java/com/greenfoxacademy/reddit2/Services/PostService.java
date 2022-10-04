package com.greenfoxacademy.reddit2.Services;

import com.greenfoxacademy.reddit2.Models.Post;

import java.util.List;

public interface PostService {

    void post(String title, String url, Long id);

    List<Post> findAll();

}
