package org.maximum0.post.domain.application.interfaces;

import org.maximum0.post.domain.Post;
import org.maximum0.user.domain.User;

public interface LikeRepository {
    boolean isAlreadyLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

}
