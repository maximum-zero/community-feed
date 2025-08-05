package org.maximum0.post.repository.entity.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.post.domain.Post;
import org.maximum0.user.domain.User;

@Entity
@Table(name = "tb_user_post_queue")
@NoArgsConstructor
@Getter
public class UserPostQueueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long postId;
    private Long authorId;

    public UserPostQueueEntity(Long userId, Long postId, Long authorId) {
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
    }

}
