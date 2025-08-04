package org.maximum0.post.repository.jpa;

import org.maximum0.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

}
