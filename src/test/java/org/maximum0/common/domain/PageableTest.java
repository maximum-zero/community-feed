package org.maximum0.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PageableTest {

    @DisplayName("페이지 index 가 null 이면 offset 은 0을 반환한다")
    @Test
    void givenPageableIndexIsNull_whenGetOffset_thenShouldBeReturn0() {
        // Given
        Pageable pageable = new Pageable();

        // When
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // Then
        assertEquals(0, offset);
        assertEquals(10, limit);
    }

    @DisplayName("페이지 index 가 2이고 사이즈가 10이면 offset 은 10을 반환한다")
    @Test
    void givenPageableIndexIs2Size10_whenGetOffset_thenShouldBeReturn10() {
        // Given
        Pageable pageable = new Pageable(2, 10);

        // When
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // Then
        assertEquals(10, offset);
        assertEquals(10, limit);
    }

}
