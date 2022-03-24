package pl.szymonexcersiseapi.nullpointerexcersiseapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostIdIn(List<Long> ids);
}
