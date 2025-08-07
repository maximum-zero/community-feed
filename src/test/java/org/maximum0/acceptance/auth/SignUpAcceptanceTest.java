package org.maximum0.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maximum0.acceptance.utils.AcceptanceTestTemplate;
import org.maximum0.auth.application.dto.SendEmailRequestDto;

public class SignUpAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "maximum.zero95@gmail.com";

    @BeforeEach
    void setUp() {
        super.cleanUp();
    }

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

    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() {
        // Given
        SendEmailRequestDto dto = new SendEmailRequestDto("email#email");

        // When
        Integer code = requestSendEmail(dto);

        // Then
        assertEquals(400, code);
    }

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

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        // Given
        requestSendEmail(new SendEmailRequestDto(email));

        // When
        Integer code = requestVerifyEmail("not email", "token");

        // Then
        assertEquals(400, code);
    }

}
