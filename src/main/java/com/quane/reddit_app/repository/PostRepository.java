package com.quane.reddit_app.repository;

import com.quane.reddit_app.model.Post;
import com.quane.reddit_app.model.Subreddit;
import com.quane.reddit_app.model.User;
//import com.sun.xml.internal.stream.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);

    List<Post> findAllBySubreddit(Subreddit subreddit);
}
