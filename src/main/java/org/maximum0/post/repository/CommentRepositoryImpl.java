package org.maximum0.post.repository;

import lombok.RequiredArgsConstructor;
import org.maximum0.post.application.interfaces.CommentRepository;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.post.repository.entity.comment.CommentEntity;
import org.maximum0.post.repository.jpa.JpaCommentRepository;
import org.maximum0.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Transactional
    @Override
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);
        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
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
