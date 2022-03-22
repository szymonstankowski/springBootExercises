package pl.szymonexcersiseapi.nullpointerexcersiseapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //sposoby definiowania metody
    //#1 - za pomoca query
    //@Query("select p from Post p where title = ?1")
    //List<Post> findAllByTitle(String title);


    //#2
    //@Query("select p from Post p where title =: title")
    //List<Post> findAllByTitle(String title);

    //#3 za pomoca samej nazwy metody
    List<Post>findAllByTitle(String title);

}
