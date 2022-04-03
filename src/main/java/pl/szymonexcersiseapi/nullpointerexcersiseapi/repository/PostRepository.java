package pl.szymonexcersiseapi.nullpointerexcersiseapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    // pobieranie wszystkich postow - left join uzyty w celu pobrania wszystkich postow nawet takich,
    // ktore nie maja komentarzy, interfejs Pageable uzyty w celu stronicowania i pobrania rzadanej ilosci postow
    // z konkretnej strony
    // w tej sytuacji generowane jest jedno zapytanie pobierajace posty z komentarzami ale pobierane sa wszystkie posty z db
    // czyli np 100k postow i dopiero pozniej sa one obcinane np do 10, nie jest to optymalne
    @Query("select p from Post p" +
           " left join fetch p.comment")
    List<Post> findAllPosts(Pageable page);



}
