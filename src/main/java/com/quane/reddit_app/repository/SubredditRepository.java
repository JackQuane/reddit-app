package com.quane.reddit_app.repository;

import com.quane.reddit_app.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    <T> Optional<Subreddit> findByName(String subredditName);
}
