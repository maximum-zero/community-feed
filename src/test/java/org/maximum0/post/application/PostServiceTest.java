package org.maximum0.post.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.post.application.dto.LikeRequestDto;
import org.maximum0.post.application.dto.UpdatePostRequestDto;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.PostPublicationState;

class PostServiceTest extends PostApplicationTestTemplate {

    @DisplayName("게시글 DTO 로 게시글 생성 후 ID로 게시글을 조회할 수 있다")
    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // When
        Post savedPost = postService.createPost(createPostRequestDto);

        // Then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
        assertEquals(postContent, post.getContent());
    }

    @DisplayName("게시글을 생성하고 수정하면 수정한 게시글을 반환한다")
    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        // When
        String updatedContent = "this is a Update Content";
        PostPublicationState updatedState = PostPublicationState.PRIVATE;
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(createPostRequestDto.authorId(), updatedContent, updatedState);

        Post updatedPost = postService.updatePost(post.getId(), updateDto);

        // Then
        assertEquals(post.getId(), updatedPost.getId());
        assertEquals(post.getAuthor(), updatedPost.getAuthor());
        assertEquals(updatedContent, updatedPost.getContent());
        assertEquals(updatedState, updatedPost.getState());
    }

    @DisplayName("게시글을 생성하고 좋아요를 하면 게시글의 좋아요 개수가 1이 된다")
    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLikeCountIsOne() {
        // When
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(likeDto);

        // Then
        assertEquals(1, post.getLikeCount());
    }

    @DisplayName("게시글을 생성하고 좋아요를 한 뒤 게시글의 좋아요를 해제하면 개수가 0이 된다")
    @Test
    void givenCreatedPostAndLike_whenUnLiked_thenReturnPostWithLikeCountIsZero() {
        // Given
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(likeDto);

        // When
        postService.unlikePost(likeDto);

        // Then
        assertEquals(0, post.getLikeCount());
    }

    @DisplayName("게시글을 생성하고 게시글의 좋아요를 해제하면 개수가 음수가 아닌 0이 된다")
    @Test
    void givenCreatedPost_whenUnLiked_thenReturnPostWithLikeCountIsZero() {
        // When
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.unlikePost(likeDto);

        // Then
        assertEquals(0, post.getLikeCount());
    }

    @DisplayName("작성자가 좋아요를 하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCreateComment_whenLikeSelf_thenThrowError() {
        // When & Then
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), user.getId());
        assertThrows(IllegalArgumentException.class, () -> postService.likePost(likeDto));
    }

}
