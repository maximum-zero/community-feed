package org.maximum0.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @DisplayName("유효한 텍스트로 생성시 해당 텍스트를 반환한다")
    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // Given
        String text = "this is a Content";

        // When
        PostContent content = new PostContent(text);

        // Then
        assertEquals(text, content.getContentText());
    }

    @DisplayName("텍스트의 길이가 500을 초과하면 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenContentLengthIsOver_whenCreated_thenThrowError(String value) {
        // Given
        String text = value.repeat(501);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @DisplayName("텍스트의 길이가 5보다 작으면 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenContentLengthIsUnder_whenCreated_thenThrowError(String value) {
        // Given
        String text = value.repeat(4);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @DisplayName("텍스트가 빈 값이면 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @DisplayName("생성 이후 유효한 텍스트로 수정 시 해당 텍스트를 반환한다")
    @Test
    void givenCreated_whenUpdatedContentLengthIsOk_thenReturnTextContent() {
        // Given
        PostContent content = new PostContent("this is a Content");
        String text = "this is a new Content";

        // When
        content.updateContent(text);

        // Then
        assertEquals(text, content.getContentText());
    }

    @DisplayName("생성 이후 텍스트의 길이가 500을 초과하는 값으로 수정 시 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenCreated_whenUpdatedContentLengthIsOver_thenThrowError(String value) {
        // Given
        PostContent content = new PostContent("this is a Content");
        String text = value.repeat(501);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(text));
    }

    @DisplayName("생성 이후 텍스트의 길이가 5보다 작은 값으로 수정 시 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenCreated_whenUpdatedContentLengthIsUnder_thenThrowError(String value) {
        // Given
        PostContent content = new PostContent("this is a Content");
        String text = value.repeat(4);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(text));
    }

    @DisplayName("생성 이후 텍스트가 빈 값으로 수정 시 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    void givenCreated_whenUpdatedContentLengthIsEmpty_thenThrowError(String value) {
        PostContent content = new PostContent("this is a Content");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }

}
