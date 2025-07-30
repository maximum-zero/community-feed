package org.maximum0.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.maximum0.post.domain.application.interfaces.CommentRepository;
import org.maximum0.post.domain.comment.Comment;

public class FakeCommentRepository implements CommentRepository {
    private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            store.put(comment.getId(), comment);
        }

        Long id = store.size() + 1L;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(), comment.getContentObject());
        store.put(id, newComment);
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
