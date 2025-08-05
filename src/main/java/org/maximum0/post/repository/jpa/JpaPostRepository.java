package org.maximum0.post.repository.jpa;

import java.util.List;
import org.maximum0.post.domain.Post;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    @Query("""
        SELECT p.id
        FROM PostEntity p
        WHERE p.author.id = :authorId
    """)
    List<Long> findAllPostIdsByAuthorId(Long authorId);

    @Modifying
    @Query("""
        UPDATE PostEntity p
        SET p.content = :#{#post.getContent()},
            p.state = :#{#post.getState()},
            p.updatedAt = current_timestamp
         WHERE p.id = :#{#post.getId()}
    """)
    void updatePost(Post post);

    @Modifying
    @Query("""
        UPDATE PostEntity p
        SET p.likeCount = p.likeCount + :likeCount,
            p.updatedAt = current_timestamp
        WHERE p.id = :postId
    """)
    void updateLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query("""
        UPDATE PostEntity p
        SET p.commentCount = p.commentCount + 1
        WHERE p.id = :postId
    """)
    void increaseCommentCount(Long postId);
}
