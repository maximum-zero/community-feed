package org.maximum0.post.repository.jpa;

import org.maximum0.post.repository.entity.like.LikeEntity;
import org.maximum0.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
