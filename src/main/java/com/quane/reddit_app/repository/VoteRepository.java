package com.quane.reddit_app.repository;

import com.quane.reddit_app.model.Post;
import com.quane.reddit_app.model.User;
import com.quane.reddit_app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    //find the most recent vote by post and user information
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
