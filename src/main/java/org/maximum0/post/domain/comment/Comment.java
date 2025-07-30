package org.maximum0.post.domain.comment;

import org.maximum0.common.PositiveIntegerCounter;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.CommentContent;
import org.maximum0.post.domain.content.Content;
import org.maximum0.user.domain.User;

public class Comment {
    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCounter;

    public static Comment createComment(Post post, User author, String content) {
        return new Comment(null, post, author, new CommentContent(content));
    }

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
        this.likeCounter = new PositiveIntegerCounter();
    }

    public void updateComment(User user, String updatedContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updatedContent);
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

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public int getLikeCount() {
        return likeCounter.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public Content getContentObject() {
        return content;
    }

}
