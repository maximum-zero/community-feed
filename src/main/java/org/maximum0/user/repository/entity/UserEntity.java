package org.maximum0.user.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.common.domain.PositiveIntegerCounter;
import org.maximum0.common.repository.entity.TimeBaseEntity;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInformation;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate registerAt;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.userName();
        this.profileImage = user.profileImageUrl();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInformation(new UserInformation(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }

}