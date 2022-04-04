package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.PostDto;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.PostDtoMapper;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    //przekazywany parametr page musi byc typu Integer poniewaz int nie przyjmuje nulla i powodowalo to ze ten "opcjonalny" parametr wcale taki nei byl /
    // i swagger dawal blad "message": "Direction must not be null!",
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort){
        // zabezpieczenie pageNumber rowna sie page jesli page jest wieksze/rowne 0 lub(?) rowne page do ktorego przypisywane jest 0
        // i sprawdzenie czy page nie jest nullem
        int pageNumber = page != null && page  >= 0 ? page : 0;

        Sort.Direction sortDirection = sort != null ? sort: Sort.Direction.ASC;

        return PostDtoMapper.mapToPostDto(postService.getPosts(pageNumber,sortDirection));

    }
    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page  >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort: Sort.Direction.ASC;
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.getSinglePost(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post){
        return postService.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id){
        postService.deletePost(id);
    }
}
