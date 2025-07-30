package org.maximum0.post.domain.application.dto;

public record UpdateCommentRequestDto(
        Long id,
        Long postId,
        Long authorId,
        String content
) {

}
