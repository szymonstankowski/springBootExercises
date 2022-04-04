package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto;

import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDtoMapper {

    public CommentDtoMapper() {
    }

    public static List<CommentDto> mapToCommentDto(List<Comment>comments){
        return comments.stream()
                .map(CommentDtoMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    public static CommentDto mapToCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .content(comment.getContent())
                .created(comment.getCreated())
                .build();
    }
}
