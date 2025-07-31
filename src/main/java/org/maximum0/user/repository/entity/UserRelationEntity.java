package org.maximum0.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "tb_user_relation")
@NoArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class)
public class UserRelationEntity extends TimeBaseEntity {
    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;
}
