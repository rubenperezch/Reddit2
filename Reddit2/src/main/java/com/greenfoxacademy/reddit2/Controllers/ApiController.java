package com.greenfoxacademy.reddit2.Controllers;

import com.greenfoxacademy.reddit2.DTOs.PostDTORequest;
import com.greenfoxacademy.reddit2.DTOs.PostListDTORequest;
import com.greenfoxacademy.reddit2.Services.PostServiceImpl;
import com.greenfoxacademy.reddit2.Services.UserServiceImpl;
import com.greenfoxacademy.reddit2.Services.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    PostServiceImpl postServiceImpl;

    UserServiceImpl userServiceImpl;

    VoteServiceImpl voteServiceImpl;

    @Autowired
    public ApiController(PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl, VoteServiceImpl voteServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.voteServiceImpl = voteServiceImpl;
    }

    @PutMapping("/post")
    public ResponseEntity postApi(@RequestBody PostDTORequest postDTORequest) {

        return postServiceImpl.postApi(postDTORequest);
    }

    @PostMapping("/postList")
    public ResponseEntity postListApi(@RequestBody PostListDTORequest postListDTORequest) {
        return postServiceImpl.postListApi(postListDTORequest);
    }

    @PostMapping("/postList/{id}")
    public ResponseEntity postListUserApi(@PathVariable (required = false) Long id, @RequestBody PostListDTORequest postListDTORequest) {
        return postServiceImpl.postListUserApi(id,postListDTORequest);
    }

}
