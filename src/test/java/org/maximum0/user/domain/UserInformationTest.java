package org.maximum0.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInformationTest {

    @DisplayName("유효한 사용자 이름과 프로필 이미지로 생성 시 예외가 발생하지 않는다")
    @Test
    void givenUserNameAndProfileImage_whenCreated_thenThrowNoting() {
        // Given
        String userName = "maximum0";
        String profileImageUrl = "";

        // Then
        assertDoesNotThrow(() -> new UserInformation(userName, profileImageUrl));
    }

    @DisplayName("빈 사용자 이름과 프로필 이미지로 생성 시 IllegalArgumentException 예외가 발생한다")
    @Test
    void givenBlankUserNameAndProfileImage_whenCreated_thenThrowIllegalArgumentException() {
        // Given
        String userName = "";
        String profileImageUrl = "";

        // Then
        assertThrows(IllegalArgumentException.class, () -> new UserInformation(userName, profileImageUrl));
    }

}