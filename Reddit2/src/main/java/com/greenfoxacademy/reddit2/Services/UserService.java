package com.greenfoxacademy.reddit2.Services;

import com.greenfoxacademy.reddit2.Models.User;

public interface UserService {

    Boolean existsUserByUsername(String username);

    Boolean loginUser(String username, String password);

    User findFirstByUsername(String username);

    User findFirstById(Long id);
}
