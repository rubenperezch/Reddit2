package com.greenfoxacademy.reddit2.Repositories;

import com.greenfoxacademy.reddit2.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByUsername(String username);

    User findFirstByUsername(String username);

    User findFirstById(Long id);
}
