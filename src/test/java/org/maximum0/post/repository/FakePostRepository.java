package org.maximum0.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.maximum0.post.domain.Post;
import org.maximum0.post.application.interfaces.PostRepository;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }

        Long id = store.size() + 1L;
        Post newPost = new Post(id, post.getAuthor(), post.getContentObject(), post.getState());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Post publish(Post post) {
        return save(post);
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
