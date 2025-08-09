package org.maximum0.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.maximum0.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static org.maximum0.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.acceptance.utils.AcceptanceTestTemplate;
import org.maximum0.auth.application.dto.LoginRequestDto;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "maximum0@gmail.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @DisplayName("이메일과 비밀번호로 로그인하면 토큰을 반환한다")
    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        // Given
        LoginRequestDto dto = new LoginRequestDto(email, "1234");

        // When
        String token = requestLoginGetToken(dto);

        // Then
        assertNotNull(token);
    }

    @DisplayName("이메일과 잘못된 비밀번호로 로그인하면 0이 아닌 코드를 반환한다")
    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeIsNotZero() {
        // Given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password");

        // When
        Integer code = requestLoginGetResponseCode(dto);

        // Then
        assertEquals(400, code);
    }

}
