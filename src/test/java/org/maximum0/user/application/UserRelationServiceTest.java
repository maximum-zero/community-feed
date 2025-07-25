package org.maximum0.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.dto.FollowUserRequestDto;
import org.maximum0.user.application.interfaces.UserRelationRepository;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.repository.FakeUserRelationRepository;
import org.maximum0.user.repository.FakeUserRepository;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("maximum0", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @DisplayName("사용자1이 사용자2를 팔로우 하면 각 팔로잉 팔로우 카운트가 증가한다")
    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // When
        userRelationService.follow(requestDto);

        // Then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @DisplayName("사용자1이 사용자2를 팔로우 하고 한번 더 팔로우 하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenThrowError() {
        // Given
        userRelationService.follow(requestDto);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @DisplayName("자기 자신을 팔로우하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCreateOneUser_whenFollow_thenThrowError() {
        // Given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @DisplayName("사용자1이 사용자2를 팔로우 하고 언팔로우 하면 각 팔로잉 팔로우 카운트가 감소한다")
    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserFollowSaved() {
        // Given
        userRelationService.follow(requestDto);

        // When
        userRelationService.unfollow(requestDto);

        // Then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @DisplayName("팔로우하지 않은 사용자를 언팔로우하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCreateTwoUser_whenUnfollow_thenThrowError() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @DisplayName("자기 자신을 언팔로우하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCreateOneUser_whenUnfollowSelf_thenThrowError() {
        // Given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }

}
