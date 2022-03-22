package pl.szymonexcersiseapi.nullpointerexcersiseapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comment;

}
