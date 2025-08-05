package org.maximum0.post.repository.jpa;

import org.maximum0.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {
    void deleteAllByUserIdAndAuthorId(Long userId, Long targetId);
}
