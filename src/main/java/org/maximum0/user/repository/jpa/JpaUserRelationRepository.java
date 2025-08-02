package org.maximum0.user.repository.jpa;

import org.maximum0.user.repository.entity.UserRelationEntity;
import org.maximum0.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
