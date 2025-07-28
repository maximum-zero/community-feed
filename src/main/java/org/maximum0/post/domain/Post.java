package org.maximum0.post.domain;

import org.maximum0.common.PositiveIntegerCounter;
import org.maximum0.post.domain.content.Content;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.domain.User;

public class Post {
    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCounter;
    private PostPublicationState state;

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
        this.state = state;
    }

    public void updatePost(User user, String updatedContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updatedContent);
        this.state = state;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCounter.increase();
    }

    public void unlike(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCounter.decrease();
    }

    public int getLikeCount() {
        return likeCounter.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
