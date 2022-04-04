package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.CommentDto;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto.CommentDtoMapper;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.CommentRepository;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentDto> getComments(){
        return CommentDtoMapper.mapToCommentDto(commentService.getComments());
    }
}
