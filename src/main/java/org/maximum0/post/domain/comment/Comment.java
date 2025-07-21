package org.maximum0.post.domain.comment;

import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.Content;
import org.maximum0.user.domain.User;

public class Comment {
    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;

    public Comment(Long id, Post post, User author, Content content) {
        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
    }
}
