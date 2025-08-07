package org.maximum0.auth.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "natty#@naver", "안녕@하세.요"})
    void givenInvalidEmail_whenCreate_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@co.kr", "ab@naver.com", "natty@naver.com", "maximum.zero95@gmail.com"})
    void givenValidEmail_whenCreate_thenReturnEmail(String value) {
        // Given

        // When
        Email email = Email.createEmail(value);

        // Then
        assertEquals(value, email.getValue());
    }
}
