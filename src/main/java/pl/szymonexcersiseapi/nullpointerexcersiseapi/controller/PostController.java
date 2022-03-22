package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.PostDto;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

import static pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.PostDtoMapper.mapToPostDtos;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/posts")
    public List<PostDto> getPosts(){
        return PostDtoMapper.mapToPostDtos(postService.getPosts());

    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.getPost(id);
    }
}
