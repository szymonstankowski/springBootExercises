package pl.szymonexcersiseapi.nullpointerexcersiseapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.CommentRepository;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    public static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page){
       return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE));
    }

    public Post getSinglePost(long id){
        return postRepository.findById(id)
                .orElseThrow();
    }

    public List<Post> getPostsWithComments(int page) {
        // pobieramy wszystkie posty i zapisujemy je do nowej listy postow
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE));
        // z listy allPost za pomoca stream'a wyjmujemy wszystkie ID postow jakie ona zawiera i zapisujemy do listy z ID
        List<Long> ids = allPosts.stream()
                .map(Post :: getId)
                .collect(Collectors.toList());
        // pobieranie z bazy komentarzy ktore zmapowane sa z ID postow zawartych w liscie allPost pobranych wyzej i
        // zapisanie ich do listy comments
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        // petla iterujaca po liscie postow i zapisujaca w liscie allPost wartosci post komentarzchyba ze
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

        // metoda filtrujaca komentarze po id i zwracahaca nowa liste odfiltrowanych komentarzy
    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());

    }
}
