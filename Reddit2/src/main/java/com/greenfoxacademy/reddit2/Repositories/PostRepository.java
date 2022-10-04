package com.greenfoxacademy.reddit2.Repositories;

import com.greenfoxacademy.reddit2.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findFirstById(Long id);

}
