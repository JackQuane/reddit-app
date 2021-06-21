package com.quane.reddit_app.service;

import com.quane.reddit_app.dto.SubredditDto;
import com.quane.reddit_app.exceptions.SpringRedditException;
import com.quane.reddit_app.mapper.SubredditMapper;
import com.quane.reddit_app.model.Subreddit;
import com.quane.reddit_app.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());

    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
            .orElseThrow(() -> new SpringRedditException("No subreddit found with id: " + id));

        return subredditMapper.mapSubredditToDto(subreddit);
    }

    //Using Mapstruct mapping instead (SubredditMapper)

    //map from storage data type to dto
//    private SubredditDto mapToDto(Subreddit subreddit) {
//        return SubredditDto.builder().name(subreddit.getName())
//                .id(subreddit.getId())
//                .numberOfPosts(subreddit.getPosts().size())
//                .build();
//    }

    //map from dto to storage data type
//    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
//        //builder design pattern allows construction of obj step by step
//        return Subreddit.builder().name(subredditDto.getName())
//                .description(subredditDto.getDescription())
//                .build();
//    }


}
