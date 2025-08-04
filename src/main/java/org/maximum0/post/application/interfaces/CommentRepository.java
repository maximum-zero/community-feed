package org.maximum0.post.application.interfaces;

import org.maximum0.post.domain.comment.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
    Comment findById(Long id);

}
