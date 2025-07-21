package org.maximum0.user.domain;

import java.util.Objects;

public class User {
    private final Long id;
    private final UserInformation userInformation;
    private final UserRelationCounter followingCount;
    private final UserRelationCounter followerCount;

    public User(Long id, String userName, String profileImageUrl) {
        this.id = id;
        this.userInformation = new UserInformation(userName, profileImageUrl);
        this.followingCount = new UserRelationCounter();
        this.followerCount = new UserRelationCounter();
    }

    public User(Long id, UserInformation userInformation) {
        this.id = id;
        this.userInformation = userInformation;
        this.followingCount = new UserRelationCounter();
        this.followerCount = new UserRelationCounter();
    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unFollow(User targetUser) {
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
