package org.maximum0.auth.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RandomTokenGeneratorTest {

    @Test
    void whenGenerateToken_thenReturnTokenWithCorrectLength() {
        // When
        String token = RandomTokenGenerator.generateToken();

        // Then
        assertNotNull(token);
        assertEquals(16, token.length());
    }

    @Test
    void whenGenerateToken_thenReturnTokenWithValidCharacters() {
        // When
        String token = RandomTokenGenerator.generateToken();

        // Then
        assertNotNull(token);
        assertTrue(token.matches("[0-9A-Za-z]{16}"));
    }

    @Test
    void whenGenerateTokenMultipleTimes_thenReturnTokenUniqueTokens() {
        // When
        String token1 = RandomTokenGenerator.generateToken();
        String token2 = RandomTokenGenerator.generateToken();

        // Then
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2);
    }

}
