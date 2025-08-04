package org.maximum0.post.application.interfaces;

import org.maximum0.post.domain.Post;

public interface PostRepository {
    Post save(Post post);
    Post findById(Long id);

}
