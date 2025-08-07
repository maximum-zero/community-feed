package org.maximum0.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.acceptance.utils.AcceptanceTestTemplate;
import org.maximum0.auth.application.dto.CreateUserAuthRequestDto;
import org.maximum0.auth.application.dto.SendEmailRequestDto;
import org.maximum0.auth.domain.UserRole;

public class SignUpAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "maximum.zero95@gmail.com";

    @BeforeEach
    void setUp() {
        super.cleanUp();
    }

    @DisplayName("이메일을 전송하면 인증 토큰이 생성된다")
    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved() {
        // Given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        // When
        Integer code = requestSendEmail(dto);

        // Then
        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);
    }

    @DisplayName("유효하지 않는 이메일을 전송하면 에러가 발생된다")
    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() {
        // Given
        SendEmailRequestDto dto = new SendEmailRequestDto("email#email");

        // When
        Integer code = requestSendEmail(dto);

        // Then
        assertEquals(400, code);
    }

    @DisplayName("이메일을 전송 후 인증하면 이메일은 인증된다")
    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));

        // When
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email, token);

        // Then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(0, code);
        assertTrue(isEmailVerified);
    }

    @DisplayName("이메일을 전송하고 올바르지 않는 토큰으로 인증 시 에러가 발생된다")
    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));

        // When
        Integer code = requestVerifyEmail(email, "wrong token");

        // Then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400, code);
        assertFalse(isEmailVerified);
    }

    @DisplayName("이미 인증한 이메일을 재인증하면 에러가 발생한다")
    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // When
        Integer code = requestVerifyEmail(email, token);

        // Then
        assertEquals(400, code);
    }

    @DisplayName("발송한 이메일과 다른 이메일로 인증하면 에러가 발생한다")
    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));

        // When
        Integer code = requestVerifyEmail("not email", "token");

        // Then
        assertEquals(400, code);
    }

    @DisplayName("인증된 이메일로 사용자 저장시 사용자가 생성된다")
    @Test
    void givenVerifiedEmail_whenRegister_thenUserRegistered() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // When
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "1234", UserRole.USER.name(), "maximum0", "http://www.naver.com");
        Integer code = registerUser(dto);

        // Then
        Long userId = getUserId(email);
        assertEquals(0, code);
        assertEquals(1L, userId);
    }

    @DisplayName("인증하지 않은 이메일로 사용자 저장시 에러가 발생한다")
    @Test
    void givenUnverifiedEmail_whenRegister_thenThrowError() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));

        // When
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "1234", UserRole.USER.name(), "maximum0", "http://www.naver.com");
        Integer code = registerUser(dto);

        // Then
        assertEquals(400, code);
    }

}
