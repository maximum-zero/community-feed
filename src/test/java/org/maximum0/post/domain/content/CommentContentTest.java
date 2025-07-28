package org.maximum0.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @DisplayName("유효한 텍스트로 생성시 해당 텍스트를 반환한다")
    @Test
    void givenContentLengthIsOk_whenCreateComment_thenReturnCommentContent() {
        // Given
        String contentText = "this is a Content";

        // When
        CommentContent content = new CommentContent(contentText);

        // Then
        assertEquals(contentText, content.getContentText());
    }

    @DisplayName("텍스트의 길이가 100을 초과하면 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenContentLengthIsOver_whenCreated_thenThrowError(String value) {
        // Given
        String text = value.repeat(101);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

    @DisplayName("텍스트의 길이가 2보다 작으면 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenContentLengthIsUnder_whenCreated_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }

    @DisplayName("텍스트가 빈 값이면 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenThrowError(String value) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }

    @DisplayName("생성 이후 유효한 텍스트로 수정 시 해당 텍스트를 반환한다")
    @Test
    void givenCreated_whenUpdatedContentLengthIsOk_thenReturnTextContent() {
        // Given
        CommentContent content = new CommentContent("this is a Content");
        String text = "this is a new Content";

        // When
        content.updateContent(text);

        // Then
        assertEquals(text, content.getContentText());
    }

    @DisplayName("생성 이후 텍스트의 길이가 100을 초과하는 값으로 수정 시 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenCreated_whenUpdatedContentLengthIsOver_thenThrowError(String value) {
        // Given
        CommentContent content = new CommentContent("this is a Content");
        String text = value.repeat(101);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(text));
    }

    @DisplayName("생성 이후 텍스트의 길이가 2보다 작은 값으로 수정 시 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings= {"a", "1", "뷁", "긁", "A"})
    void givenCreated_whenUpdatedContentLengthIsUnder_thenThrowError(String value) {
        // Given
        CommentContent content = new CommentContent("this is a Content");
        String text = value.repeat(1);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(text));
    }

    @DisplayName("생성 이후 텍스트가 빈 값으로 수정 시 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    void givenCreated_whenUpdatedContentLengthIsEmpty_thenThrowError(String value) {
        CommentContent content = new CommentContent("this is a Content");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }

}