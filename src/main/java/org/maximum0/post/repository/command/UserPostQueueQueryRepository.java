package org.maximum0.post.repository.command;

import java.util.List;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId);

}
