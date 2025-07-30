package org.maximum0.post.domain.application.dto;

public record CreateCommentRequestDto(
        Long postId,
        Long authorId,
        String content
) {

}
