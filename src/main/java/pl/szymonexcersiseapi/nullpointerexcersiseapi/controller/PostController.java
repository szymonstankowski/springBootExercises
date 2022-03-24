package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.PostDto;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;



    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) int page){
        // zabezpieczenie pageNumber rowna sie page jesli page jest wieksze/rowne 0 lub(?) rowne page do ktorego przypisywane jest 0
        int pageNumber = page >= 0 ? page : 0;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber));

    }
    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page){
        int pageNumber = page >= 0 ? page : 0;
        return postService.getPostsWithComments(pageNumber);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.getSinglePost(id);
    }
}
