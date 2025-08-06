package org.maximum0;

import static org.maximum0.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.maximum0.acceptance.steps.FeedAcceptanceSteps.requestFeed;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.acceptance.utils.AcceptanceTestTemplate;
import org.maximum0.post.application.dto.CreatePostRequestDto;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    @BeforeEach
    void setUp() {
        super.init();
    }

    @DisplayName("팔로워가 게시글을 생성하면 팔로잉 유저의 피드에 해당 게시글을 가져올 수 있습니다.")
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowingUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // Given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // When
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // Then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }
}
