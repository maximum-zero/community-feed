package org.maximum0.post.domain.comment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.CommentContent;
import org.maximum0.post.domain.content.PostContent;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInformation;

class CommentTest {
    private final UserInformation userInformation = new UserInformation("maximum0", "");
    private final User user = new User(1L, userInformation);
    private final User otherUser = new User(2L, userInformation);

    private final Post post = new Post(1L, user, new PostContent("this is a Content"), PostPublicationState.PUBLIC);

    private Comment comment;

    @BeforeEach
    void init() {
        comment = new Comment(1L, post, user, new CommentContent("this is a Comment"));
    }

    @DisplayName("생성 이후 다른 유저가 좋아요를 하면 좋아요의 개수가 1개가 된다")
    @Test
    void givenCommentCreated_whenLikeByOtherUser_thenLikeCountIsOne() {
        // When
        comment.like(otherUser);

        // Then
        assertEquals(1, comment.getLikeCount());
    }

    @DisplayName("생성 이후 자기 자신이 좋아요를 하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowError() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @DisplayName("생성 이후 다른 유저가 좋아요를 하고 좋아요를 해제하면 좋아요의 개수는 0개가 된다")
    @Test
    void givenCommentCreatedAndLikeByOtherUser_whenUnlike_thenCountIsZero() {
        // Given
        comment.like(otherUser);

        // When
        comment.unlike(otherUser);

        // Then
        assertEquals(0, comment.getLikeCount());
    }

    @DisplayName("생성 이후 좋아요를 해제하면 좋아요의 개수는 음수가 아닌 0개가 된다")
    @Test
    void givenCommentCreated_whenUnlike_thenCountIsZero() {
        // When
        comment.unlike(otherUser);

        // Then
        assertEquals(0, comment.getLikeCount());
    }

    @DisplayName("생성 이후 내용을 수정하면 수정된 내용을 반환한다")
    @Test
    void givenCommentCreated_whenUpdatedContent_thenReturnContent() {
        // Given
        String newCommentContent = "this is a new Content";

        // When
        comment.updateComment(user, newCommentContent);

        // Then
        assertEquals(newCommentContent, comment.getContent());
    }

    @DisplayName("생성 이후 다른 유저가 내용을 수정하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenPostCreated_whenUpdatedContentByOtherUser_thenThrowError() {
        // Given
        String newCommentContent = "this is a new Content";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }

}