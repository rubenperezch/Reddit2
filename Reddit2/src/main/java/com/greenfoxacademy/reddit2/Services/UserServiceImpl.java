package com.greenfoxacademy.reddit2.Services;

import com.greenfoxacademy.reddit2.Models.User;
import com.greenfoxacademy.reddit2.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public Boolean loginUser(String username, String password) {
        if (existsUserByUsername(username) == true && userRepository.findFirstByUsername(username).getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public User findFirstById(Long id) {
        return userRepository.findFirstById(id);
    }

    public void addUser(String username, String password) {
        if (existsUserByUsername(username) == false) {
            User user = new User(username, password);

            userRepository.save(user);
        } else {

        }
    }

}
