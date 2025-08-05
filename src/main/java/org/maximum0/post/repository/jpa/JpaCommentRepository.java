package org.maximum0.post.repository.jpa;

import org.maximum0.post.domain.comment.Comment;
import org.maximum0.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
    @Modifying
    @Query("""
        UPDATE CommentEntity c
        SET c.content = :#{#comment.getContent()},
            c.updatedAt = current_timestamp
        WHERE c.id = :#{#comment.getId()}
    """)
    void updateComment(Comment comment);

    @Modifying
    @Query("""
        UPDATE CommentEntity c
        SET c.likeCount = c.likeCount + :likeCount
        WHERE c.id = :commendId
    """)
    void updateLikeCount(Long commendId, Integer likeCount);
}
