package pl.szymonexcersiseapi.nullpointerexcersiseapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentDto {
    private long id;
    private long postId;
    private String content;
    private LocalDateTime created;

}
