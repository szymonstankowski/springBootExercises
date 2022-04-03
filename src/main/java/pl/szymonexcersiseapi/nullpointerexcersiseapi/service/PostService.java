package pl.szymonexcersiseapi.nullpointerexcersiseapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Comment;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.model.Post;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.CommentRepository;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    public static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort){
       return postRepository.findAllPosts(
               PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    public Post getSinglePost(long id){
        return postRepository.findById(id)
                .orElseThrow();
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        // pobieramy wszystkie posty i zapisujemy je do nowej listy postow
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort,"id")));
        // z listy allPost za pomoca stream'a wyjmujemy wszystkie ID postow jakie ona zawiera i zapisujemy do listy z ID
        List<Long> ids = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        // pobieranie z bazy komentarzy ktore zmapowane sa z ID postow zawartych w liscie allPost pobranych wyzej i
        // zapisanie ich do listy comments
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        // petla iterujaca po liscie postow i zapisujaca w liscie allPost wartosci post komentarzy
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

        // metoda filtrujaca komentarze po id i zwracajaca nowa liste odfiltrowanych komentarzy
    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());

    }

    public Post addPost(Post post) {

        return postRepository.save(post);
    }

    // adnotacje transactional mozna dodac w momencie kiedy wew metody znajduja sie dwie transakcie(metody)
    // w tym wypadku bylo to postRepository.findById(post.getId()).orElseThrow(); i postRepository.save(post);
    // po dodaniu adnotacji mozna zrezygnowac z 2 metody, jest to zwiazane z metoda Hibertane - dirty checking
    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        //return postRepository.save(post);
        return postEdited;

    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
