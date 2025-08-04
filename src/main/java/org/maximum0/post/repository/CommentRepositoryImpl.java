package org.maximum0.post.repository;

import lombok.RequiredArgsConstructor;
import org.maximum0.post.application.interfaces.CommentRepository;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.post.repository.entity.comment.CommentEntity;
import org.maximum0.post.repository.jpa.JpaCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        commentEntity = jpaCommentRepository.save(commentEntity);
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return commentEntity.toComment();
    }
}
