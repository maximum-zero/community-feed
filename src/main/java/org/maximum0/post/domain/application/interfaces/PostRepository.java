package org.maximum0.post.domain.application.interfaces;

import java.util.Optional;
import org.maximum0.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);

}
