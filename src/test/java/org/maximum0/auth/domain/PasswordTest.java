package org.maximum0.auth.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PasswordTest {

    @DisplayName("같은 비밀번호를 비교했을 때, true 를 반환한다")
    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        // Given
        Password password = Password.createEncryptPassword("1234");

        // When & Then
        assertTrue(password.matchPassword("1234"));
    }

    @DisplayName("다른 비밀번호를 비교했을 때, false 를 반환한다")
    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnFalse() {
        // Given
        Password password = Password.createEncryptPassword("12345");

        // When & Then
        assertFalse(password.matchPassword("1234"));
    }

    @DisplayName("비밀번호가 빈 값이면 IllegalArgumentException 예외를 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        // Given & Then
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }

}
