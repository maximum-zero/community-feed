package org.maximum0.post.repository.jpa;

import org.maximum0.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

}
