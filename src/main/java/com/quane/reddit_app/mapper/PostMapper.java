package com.quane.reddit_app.mapper;

import com.quane.reddit_app.dto.PostRequest;
import com.quane.reddit_app.dto.PostResponse;
import com.quane.reddit_app.model.Post;
import com.quane.reddit_app.model.Subreddit;
import com.quane.reddit_app.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    //    source and target names match.. so taken care of by mapstruct
    //    @Mapping(target = "subreddit", source = "subreddit")
    //    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);


    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
