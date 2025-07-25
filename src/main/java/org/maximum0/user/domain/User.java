package org.maximum0.user.domain;

import java.util.Objects;
import org.maximum0.common.PositiveIntegerCounter;

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

    public Long getId() {
        return this.id;
    }

    public int followerCount() {
        return this.followerCount.getCount();
    }

    public int followingCount() {
        return this.followingCount.getCount();
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public PositiveIntegerCounter getFollowingCount() {
        return followingCount;
    }

    public PositiveIntegerCounter getFollowerCount() {
        return followerCount;
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
