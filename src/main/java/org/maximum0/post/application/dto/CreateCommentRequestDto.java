package org.maximum0.post.application.dto;

public record CreateCommentRequestDto(
        Long postId,
        Long authorId,
        String content
) {

}
