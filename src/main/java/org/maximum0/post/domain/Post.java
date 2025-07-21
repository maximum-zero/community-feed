package org.maximum0.post.domain;

import org.maximum0.post.domain.content.Content;
import org.maximum0.post.domain.content.PostContent;
import org.maximum0.user.domain.User;

public class Post {
    private final Long id;
    private final User author;
    private final Content content;

    public Post(Long id, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
    }
}
