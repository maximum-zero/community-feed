package org.maximum0.post.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.post.application.dto.LikeRequestDto;
import org.maximum0.post.application.dto.UpdateCommentRequestDto;
import org.maximum0.post.domain.comment.Comment;

class CommentServiceTest extends PostApplicationTestTemplate {

    @DisplayName("댓글 DTO 로 댓글 생성 후 ID로 댓글을 조회할 수 있다")
    @Test
    void givenCommentRequestDto_whenCreate_thenReturnComment() {
        // When
        Comment comment = commentService.createComment(createCommentRequestDto);

        // Then
        assertEquals(commentContent, comment.getContent());
    }

    @DisplayName("댓글을 생성하고 수정하면 수정한 댓글을 반환한다")
    @Test
    void givenCreateComment_whenUpdate_thenReturnUpdatedComment() {
        // When
        String updatedContent = "this is a Update Content";
        UpdateCommentRequestDto updateDto = new UpdateCommentRequestDto(comment.getId(), comment.getPost().getId(), comment.getAuthor().getId(), updatedContent);

        Comment updatedComment = commentService.updateComment(updateDto);

        // Then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(updatedContent, updatedComment.getContent());
    }

    @DisplayName("댓글을 생성하고 좋아요를 하면 게시글의 좋아요 개수가 1이 된다")
    @Test
    void givenCreateComment_whenLiked_thenReturnCommentWithLikeCountIsOne() {
        // When
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);

        // Then
        assertEquals(1, comment.getLikeCount());
    }

    @DisplayName("댓글을 생성하고 좋아요를 한 뒤 댓글의 좋아요를 해제하면 개수가 0이 된다")
    @Test
    void givenCreateCommentAndLike_whenUnLiked_thenReturnCommentWithLikeCountIsZero() {
        // Given
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);

        // When
        commentService.unlikeComment(likeDto);

        // Then
        assertEquals(0, comment.getLikeCount());
    }

    @DisplayName("댓글을 생성하고 댓글의 좋아요를 해제하면 개수가 음수가 아닌 0이 된다")
    @Test
    void givenCreateComment_whenUnLiked_thenReturnCommentWithLikeCountIsZero() {
        // When
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.unlikeComment(likeDto);

        // Then
        assertEquals(0, comment.getLikeCount());
    }

    @DisplayName("작성자가 좋아요를 하면 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenCreateComment_whenLikeSelf_thenThrowError() {
        // When & Then
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), user.getId());
        assertThrows(IllegalArgumentException.class, () -> commentService.likeComment(likeDto));
    }

}