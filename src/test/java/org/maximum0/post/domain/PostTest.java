package org.maximum0.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.post.domain.content.PostContent;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInformation;

class PostTest {
    private final UserInformation userInformation = new UserInformation("maximum0", "");
    private final User user = new User(1L, userInformation);
    private final User otherUser = new User(2L, userInformation);

    private Post post;

    @BeforeEach
    void init() {
        post = new Post(1L, user, new PostContent("this is a Content"), PostPublicationState.PUBLIC);
    }

    @DisplayName("생성 이후 다른 유저가 좋아요를 하면 좋아요의 개수가 1개가 된다")
    @Test
    void givenPostCreated_whenLikeByOtherUser_thenLikeCountIsOne() {
        // When
        post.like(otherUser);

        // Then
        assertEquals(1, post.getLikeCount());
    }

    @DisplayName("생성 이후 자기 자신이 좋아요를 하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenPostCreated_whenLikeBySelf_thenThrowError() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @DisplayName("생성 이후 다른 유저가 좋아요를 하고 좋아요를 해제하면 좋아요의 개수는 0개가 된다")
    @Test
    void givenPostCreatedAndLikeByOtherUser_whenUnlike_thenCountIsZero() {
        // Given
        post.like(otherUser);

        // When
        post.unlike(otherUser);

        // Then
        assertEquals(0, post.getLikeCount());
    }

    @DisplayName("생성 이후 좋아요를 해제하면 좋아요의 개수는 음수가 아닌 0개가 된다")
    @Test
    void givenPostCreated_whenUnlike_thenCountIsZero() {
        // When
        post.unlike(otherUser);

        // Then
        assertEquals(0, post.getLikeCount());
    }

    @DisplayName("생성 이후 내용을 수정하면 수정된 내용을 반환한다")
    @Test
    void givenPostCreated_whenUpdatedContent_thenReturnContent() {
        // Given
        String newPostContent = "this is a new Content";

        // When
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        // Then
        assertEquals(newPostContent, post.getContent());
    }

    @DisplayName("생성 이후 다른 유저가 내용을 수정하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenPostCreated_whenUpdatedContentByOtherUser_thenThrowError() {
        // Given
        String newPostContent = "this is a new Content";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }

}
