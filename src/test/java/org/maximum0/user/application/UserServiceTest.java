package org.maximum0.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.fake.FakeObjectFactory;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInformation;
import org.maximum0.user.repository.FakeUserRepository;

class UserServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();

    @DisplayName("사용자 정보 DTO 로 유저 생성 후 ID로 유저를 조회할 수 있다")
    @Test
    void givenUserInformationDto_whenCreateUser_thenCanFindUser() {
        // Given
        CreateUserRequestDto dto = new CreateUserRequestDto("maximum0", "");

        // When
        User savedUser = userService.createUser(dto);

        // Then
        User foundUser = userService.getUser(savedUser.getId());
        UserInformation userInformation = foundUser.getUserInformation();

        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals(dto.userName(), userInformation.getUserName());
    }

}
