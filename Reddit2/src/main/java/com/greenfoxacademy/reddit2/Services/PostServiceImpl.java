package com.greenfoxacademy.reddit2.Services;

import com.greenfoxacademy.reddit2.DTOs.MessageDTO;
import com.greenfoxacademy.reddit2.DTOs.PostDTORequest;
import com.greenfoxacademy.reddit2.DTOs.PostListDTORequest;
import com.greenfoxacademy.reddit2.DTOs.PostListDTOResponse;
import com.greenfoxacademy.reddit2.Models.Post;
import com.greenfoxacademy.reddit2.Models.Vote;
import com.greenfoxacademy.reddit2.Repositories.PostRepository;
import com.greenfoxacademy.reddit2.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    UserRepository userRepository;
    PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void post(String title, String url, Long id) {
        Post post = new Post(title, url, userRepository.findFirstById(id));

        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Integer countVotes(Long id) {
        Integer count = 0;

        List<Vote> list = new ArrayList<>(postRepository.findFirstById(id).getListVotes());

        for (Vote vote: list) {
            if (vote.getScore() == true) {
                count = count + 1;
            } else if (vote.getScore() == false) {
                count = count - 1;
            }
        }
        return count;
    }

    //APIS

    public ResponseEntity postApi(PostDTORequest postDTORequest) {
        if(userRepository.existsUserByUsername(postDTORequest.getUsername()) == true && userRepository.findFirstByUsername(postDTORequest.getUsername()).getPassword().equals(postDTORequest.getPassword())) {

            post(postDTORequest.getTitle(), postDTORequest.getUrl(), userRepository.findFirstByUsername(postDTORequest.getUsername()).getId());

            MessageDTO message = new MessageDTO();
            message.setMessage("Post: " + postDTORequest.getTitle() + " successfully posted by " +postDTORequest.getUsername());

            return ResponseEntity.ok(message);
        } else {
            MessageDTO message = new MessageDTO();
            message.setMessage("Error: user does not exist, or password invalid");

            return ResponseEntity.status(404).body(message);
        }
    }

    public ResponseEntity postListApi(PostListDTORequest postListDTORequest) {
        if(userRepository.existsUserByUsername(postListDTORequest.getUsername()) == true && userRepository.findFirstByUsername(postListDTORequest.getUsername()).getPassword().equals(postListDTORequest.getPassword())) {

            List<Post> posts = postRepository.findAll();

            List<PostListDTOResponse> postList = new ArrayList<>();

            for (Post post: posts) {

                PostListDTOResponse postListDTOResponse = new PostListDTOResponse();
                postListDTOResponse.setTitle(post.getTitle());
                postListDTOResponse.setUrl(post.getUrl());

                postList.add(postListDTOResponse);

            }

            return ResponseEntity.ok(postList);
        } else {
            MessageDTO message = new MessageDTO();
            message.setMessage("Error: user does not exist, or password invalid");

            return ResponseEntity.status(404).body(message);
        }
    }

    public ResponseEntity postListUserApi(Long id, PostListDTORequest postListDTORequest) {
        if(userRepository.existsUserByUsername(postListDTORequest.getUsername()) == true && userRepository.findFirstByUsername(postListDTORequest.getUsername()).getPassword().equals(postListDTORequest.getPassword()) && userRepository.existsById(id) == true) {

            List<Post> posts = userRepository.findFirstById(id).getListPosts();

            List<PostListDTOResponse> postList = new ArrayList<>();

            for (Post post: posts) {

                PostListDTOResponse postListDTOResponse = new PostListDTOResponse();
                postListDTOResponse.setTitle(post.getTitle());
                postListDTOResponse.setUrl(post.getUrl());

                postList.add(postListDTOResponse);

            }

            return ResponseEntity.ok(postList);
        } else {
            MessageDTO message = new MessageDTO();
            message.setMessage("Error: user does not exist, or password invalid");

            return ResponseEntity.status(404).body(message);
        }
    }
}
