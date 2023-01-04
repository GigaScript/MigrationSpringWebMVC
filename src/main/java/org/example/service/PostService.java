package org.example.service;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all().stream()
                .filter(
                        post -> post
                                .getPostStatus() != PostStatus.REMOVED
                )
                .collect(Collectors.toList());
    }

    public Post getById(long id) {
        Optional<Post> post = repository.getById(id);
        if (post.isPresent() && post.get().getPostStatus() != PostStatus.REMOVED) {
            return post.get();
        }
        throw new NotFoundException();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            return repository.save(post);
        }
        Post originalPost = getById(post.getId());
        if (originalPost.getPostStatus() != PostStatus.REMOVED) {
            return repository.save(post);
        }
        throw new NotFoundException();
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}
