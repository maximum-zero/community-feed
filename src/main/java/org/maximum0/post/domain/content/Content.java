package org.maximum0.post.domain.content;

import org.maximum0.post.domain.common.AuditTimestamp;

public abstract class Content {
    String contentText;
    final AuditTimestamp auditTimestamp;

    protected Content(String contentText) {
        checkedContent(contentText);
        this.contentText = contentText;
        this.auditTimestamp = new AuditTimestamp();
    }

    public void updateContent(String updatedContent) {
        checkedContent(contentText);
        this.contentText = updatedContent;
        this.auditTimestamp.updatedAudit();
    }

    protected abstract void checkedContent(String contentText);

    public String getContentText() {
        return contentText;
    }
}
