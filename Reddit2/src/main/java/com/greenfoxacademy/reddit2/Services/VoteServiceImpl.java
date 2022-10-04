package com.greenfoxacademy.reddit2.Services;

import com.greenfoxacademy.reddit2.Models.Post;
import com.greenfoxacademy.reddit2.Models.Vote;
import com.greenfoxacademy.reddit2.Repositories.PostRepository;
import com.greenfoxacademy.reddit2.Repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService{

    PostRepository postRepository;

    VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, PostRepository postRepository) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
    }

    public void votePlus(Long id) {

        Vote vote = new Vote(true, postRepository.findFirstById(id));

        postRepository.findFirstById(id).setScore(postRepository.findFirstById(id).getScore()+1);

        voteRepository.save(vote);
    }

    public void voteMinus(Long id) {

        Vote vote = new Vote(false, postRepository.findFirstById(id));

        postRepository.findFirstById(id).setScore(postRepository.findFirstById(id).getScore()-1);

        voteRepository.save(vote);
    }
}
