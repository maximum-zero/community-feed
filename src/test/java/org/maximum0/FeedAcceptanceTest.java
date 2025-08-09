package org.maximum0;

import static org.maximum0.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.maximum0.acceptance.steps.FeedAcceptanceSteps.requestFeed;

import static org.junit.jupiter.api.Assertions.*;
import static org.maximum0.acceptance.steps.FeedAcceptanceSteps.requestFeedCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.acceptance.utils.AcceptanceTestTemplate;
import org.maximum0.post.application.dto.CreatePostRequestDto;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {
    private String token;

    @BeforeEach
    void setUp() {
        super.init();
        this.token = login("maximum0@gmail.com");
    }

    @DisplayName("팔로워가 게시글을 생성하면 팔로잉 유저의 피드에 해당 게시글을 가져올 수 있습니다.")
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowingUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // Given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // When
        List<GetPostContentResponseDto> result = requestFeed(this.token);

        // Then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

    @DisplayName("올바르지 않은 토큰으로 피드를 조회시 코드는 0이 아닌 값을 반환한다")
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowingUserRequestFeedWithInvalidToken_thenReturnCodeIsNotZero() {
        // Given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        requestCreatePost(dto);

        // When
        Integer code = requestFeedCode("invalid token");

        // Then
        assertEquals(400, code);
    }

}
