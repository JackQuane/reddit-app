package com.quane.reddit_app.service;

import com.quane.reddit_app.dto.CommentsDto;
import com.quane.reddit_app.exceptions.PostNotFoundException;
import com.quane.reddit_app.mapper.CommentMapper;
import com.quane.reddit_app.model.Comment;
import com.quane.reddit_app.model.NotificationEmail;
import com.quane.reddit_app.model.Post;
import com.quane.reddit_app.model.User;
import com.quane.reddit_app.repository.CommentRepository;
import com.quane.reddit_app.repository.PostRepository;
import com.quane.reddit_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        //send notification to post owner that user X posted a comment on their post
        String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
        //user that owns the post = post.getUser()
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        //user.getUsername() refers to the owner of the post.. below is incorrect
//        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
        mailService.sendMail(new NotificationEmail(authService.getCurrentUser().getUsername() + " commented on your post", user.getEmail(), message));

    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
