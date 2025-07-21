package org.maximum0.post.domain.content;

public abstract class Content {
    String contentText;

    protected Content(String contentText) {
        checkedContent(contentText);
        this.contentText = contentText;
    }

    protected abstract void checkedContent(String contentText);

    public String getContentText() {
        return contentText;
    }
}
