package org.maximum0.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maximum0.common.domain.PositiveIntegerCounter;

class PositiveIntegerCounterTest {

    @DisplayName("생성 후 Increase 시 카운트가 1이 된다")
    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        // Given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // When
        counter.increase();

        // Then
        assertEquals(1, counter.getCount());
    }

    @DisplayName("생성 후 Increase 하고 Decrease 시 카운트가 0이 된다")
    @Test
    void givenCreatedAndIncrease_whenDecrease_thenCountIsZero() {
        // Given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        // When
        counter.decrease();

        // Then
        assertEquals(0, counter.getCount());
    }

    @DisplayName("생성 후 Decrease 시 카운트가 -1이 아닌 0이 된다")
    @Test
    void givenCreated_whenDecrease_thenCountIsZero() {
        // Given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // When
        counter.decrease();

        // Then
        assertEquals(0, counter.getCount());
    }
}
