package org.maximum0.user.application.dto;

import org.maximum0.user.domain.User;

public record GetUserResponseDto(
        Long id,
        String name,
        String profileImageUrl,
        Integer followingCount,
        Integer followerCount
) {
    public GetUserResponseDto(User user) {
        this(user.getId(), user.userName(), user.profileImageUrl(), user.followingCount(), user.followerCount());
    }
}
