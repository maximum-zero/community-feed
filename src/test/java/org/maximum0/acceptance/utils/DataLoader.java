package org.maximum0.acceptance.utils;

import static org.maximum0.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.maximum0.acceptance.steps.UserAcceptanceSteps.followUser;

import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    public void load() {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto("test user", "");
        createUser(createUserRequestDto);
        createUser(createUserRequestDto);
        createUser(createUserRequestDto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
