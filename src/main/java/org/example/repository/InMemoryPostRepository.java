package org.example.repository;

import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPostRepository implements PostRepository {
    private final List<Post> posts = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Post> all() {
        return posts;
    }

    @Override
    public Optional<Post> getById(long id) {
        return findPostById(id);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(
                    counter.addAndGet(1)
            );
            posts.add(post);
            return post;
        }
        Optional<Post> originalPost = findPostById(post.getId());
        posts.set(posts.indexOf(originalPost.get()), post);
        return post;
    }

    @Override
    public void removeById(long id) {
        Optional<Post> post = findPostById(id);
        if (post.isPresent()) {
            post.get().setRemovedStatus();
        }
    }

    private Optional<Post> findPostById(long id) {
        return posts.stream()
                .filter(
                        post -> post.getId() == id
                )
                .findFirst();
    }

}