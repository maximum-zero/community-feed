package org.maximum0.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuditTimestampTest {

    @DisplayName("생성 후 업데이트 시 isEdited 는 true 가 되고, 시간은 초기 시간보다 나중이 됩니다")
    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException{
        // Given
        AuditTimestamp auditTimestamp = new AuditTimestamp();
        LocalDateTime localDateTime = auditTimestamp.getDatetime();

        Thread.sleep(10);

        // When
        auditTimestamp.updatedAudit();

        // Then
        assertTrue(auditTimestamp.isEdited());
        assertTrue(auditTimestamp.getDatetime().isAfter(localDateTime));
    }

}
