package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller;


import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.PostDto;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    private PostDtoMapper() {
    }

    public static List<PostDto> mapToPostDtos(List<Post>posts){
        return posts.stream()
                .map(post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
