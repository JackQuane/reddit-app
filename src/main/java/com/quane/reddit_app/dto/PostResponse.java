package com.quane.reddit_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String postName;
    private String url;
    private String description;
    private String userName;
    private String subredditName;
    private Integer voteCount;
    private Integer commentCount;
    //e.g. 4 minutes ago
    //uses a Kotlin time ago plugin
    private String duration;
    private boolean upVote;
    private boolean downVote;

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setSubredditName(String subredditName) {
//        this.subredditName = subredditName;
//    }
}
