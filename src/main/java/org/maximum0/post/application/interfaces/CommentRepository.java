package org.maximum0.post.application.interfaces;

import java.util.Optional;
import org.maximum0.post.domain.comment.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);

}
