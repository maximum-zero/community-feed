package org.maximum0.post.application;

import org.junit.jupiter.api.BeforeEach;
import org.maximum0.fake.FakeObjectFactory;
import org.maximum0.post.application.dto.CreateCommentRequestDto;
import org.maximum0.post.application.dto.CreatePostRequestDto;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.application.UserService;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.domain.User;

public class PostApplicationTestTemplate {
    protected final UserService userService = FakeObjectFactory.getUserService();
    protected final PostService postService = FakeObjectFactory.getPostService();
    protected final CommentService commentService = FakeObjectFactory.getCommentService();

    protected User user;
    protected User otherUser;

    protected CreatePostRequestDto createPostRequestDto;
    protected String postContent = "this is a Content";
    protected Post post;
    protected CreateCommentRequestDto createCommentRequestDto;
    protected String commentContent = "this is a Comment";
    protected Comment comment;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("maximum0", "");
        this.user = userService.createUser(dto);
        this.otherUser = userService.createUser(dto);

        this.createPostRequestDto = new CreatePostRequestDto(user.getId(), postContent, PostPublicationState.PUBLIC);
        post = postService.createPost(this.createPostRequestDto);

        this.createCommentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContent);
        comment = commentService.createComment(this.createCommentRequestDto);
    }
}
