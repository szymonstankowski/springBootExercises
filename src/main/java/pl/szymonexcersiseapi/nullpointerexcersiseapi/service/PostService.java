package pl.szymonexcersiseapi.nullpointerexcersiseapi.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts(){
       return postRepository.findAll();
    }

    public Post getPost(long id){
        return postRepository.getById(id);
    }

}
