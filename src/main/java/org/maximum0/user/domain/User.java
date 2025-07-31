package org.maximum0.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.maximum0.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private final Long id;
    private final UserInformation userInformation;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, String userName, String profileImageUrl) {
        this.id = id;
        this.userInformation = new UserInformation(userName, profileImageUrl);
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public User(Long id, UserInformation userInformation) {
        this.id = id;
        this.userInformation = userInformation;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    public String userName() {
        return this.userInformation.getUserName();
    }

    public String profileImageUrl() {
        return this.userInformation.getProfileImageUrl();
    }

    public int followerCount() {
        return this.followerCount.getCount();
    }

    public int followingCount() {
        return this.followingCount.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
