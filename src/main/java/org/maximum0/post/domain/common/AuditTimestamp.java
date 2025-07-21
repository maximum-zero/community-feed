package org.maximum0.post.domain.common;

import java.time.LocalDateTime;

public class AuditTimestamp {
    private boolean isEdited;
    private LocalDateTime datetime;

    public AuditTimestamp() {
        this.isEdited = false;
        this.datetime = LocalDateTime.now();
    }

    public void updatedAudit() {
        this.isEdited = true;
        this.datetime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }
}
