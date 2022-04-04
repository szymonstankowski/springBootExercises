package pl.szymonexcersiseapi.nullpointerexcersiseapi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.CommentRepository;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    public static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public List<Comment> getComments() {
        return commentRepository.findAll();
    }
}
