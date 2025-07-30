package org.maximum0.post.domain.application.dto;

import org.maximum0.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(
        Long id,
        Long authorId,
        String content,
        PostPublicationState state
) {

}
