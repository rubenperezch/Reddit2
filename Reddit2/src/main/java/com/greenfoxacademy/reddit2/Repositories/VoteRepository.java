package com.greenfoxacademy.reddit2.Repositories;

import com.greenfoxacademy.reddit2.Models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
