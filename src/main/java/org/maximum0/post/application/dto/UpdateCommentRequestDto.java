package org.maximum0.post.application.dto;

public record UpdateCommentRequestDto(
        Long id,
        Long postId,
        Long authorId,
        String content
) {

}
