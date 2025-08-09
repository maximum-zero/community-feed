package org.maximum0.auth.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenProviderTest {
    private final String secretKey = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @DisplayName("유효한 유저와 규칙으로 토큰을 생성하면 올바른 토큰을 반환한다")
    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {
        // Given
        Long userId = 1L;
        String role = UserRole.ADMIN.name();;

        // When
        String token = tokenProvider.createToken(userId, role);

        // Then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    void givenInValidToken_whenGetUserId_thenThrowError() {
        // Given
        String invalidToken = "invalid token";

        // When & Then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }

}
