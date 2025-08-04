package org.maximum0.post.application.dto;

public record UpdateCommentRequestDto(
        Long postId,
        Long authorId,
        String content
) {

}
