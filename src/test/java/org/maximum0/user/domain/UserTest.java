package org.maximum0.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInformation userInformation = new UserInformation("maximum0", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInformation);
        user2 = new User(2L, userInformation);
    }

    @DisplayName("서로 다른 두 사용자가 같지 않다")
    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        // When
        boolean isSame = user1.equals(user2);

        // Then
        assertFalse(isSame);
    }

    @DisplayName("동일한 ID를 가진 두 사용자가 같다")
    @Test
    void givenTwoSameUser_whenEqual_thenReturnTrue() {
        // Given
        User sameUser = new User(1L, userInformation);

        // When
        boolean isSame = user1.equals(sameUser);

        // Then
        assertTrue(isSame);
    }

    @DisplayName("서로 다른 두 사용자의 해시코드가 같지 않다")
    @Test
    void givenTwoUser_whenHashCode_thenReturnFalse() {
        // When
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();

        // Then
        assertNotEquals(hashcode1, hashcode2);
    }

    @DisplayName("동일한 ID를 가진 두 사용자의 해시코드가 같다")
    @Test
    void givenTwoSameUser_whenHashCode_thenReturnTrue() {
        // Given
        User sameUser = new User(1L, userInformation);

        // When
        int hashcode1 = user1.hashCode();
        int sameUserHashcode = sameUser.hashCode();

        // Then
        assertEquals(hashcode1, sameUserHashcode);
    }

    @DisplayName("사용자1이 사용자2를 팔로우 하면 각 팔로잉 팔로우 카운트가 증가한다")
    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        // When
        user1.follow(user2);

        // Then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());

        assertEquals(0, user2.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @DisplayName("사용자1이 사용자2를 팔로우하고 언팔로우 하면 각 팔로잉 팔로우 카운트가 감소한다")
    @Test
    void givenTwoUserUser1FollowUser2_whenUnFollow_thenDecreaseUserCount() {
        // Given
        user1.follow(user2);

        // When
        user1.unfollow(user2);

        // Then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @DisplayName("팔로우하지 않은 사용자가 언팔로우 해도 카운트가 -1이 되지 않고 0을 유지한다")
    @Test
    void givenTwoUser_whenUnFollow_thenNotDecreaseUserCount() {
        // When
        user1.unfollow(user2);

        // Then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }
}