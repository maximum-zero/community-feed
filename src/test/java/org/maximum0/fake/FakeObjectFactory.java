package org.maximum0.fake;

import org.maximum0.post.domain.application.CommentService;
import org.maximum0.post.domain.application.PostService;
import org.maximum0.post.domain.application.interfaces.CommentRepository;
import org.maximum0.post.domain.application.interfaces.LikeRepository;
import org.maximum0.post.domain.application.interfaces.PostRepository;
import org.maximum0.post.repository.FakeCommentRepository;
import org.maximum0.post.repository.FakeLikeRepository;
import org.maximum0.post.repository.FakePostRepository;
import org.maximum0.user.application.UserRelationService;
import org.maximum0.user.application.UserService;
import org.maximum0.user.application.interfaces.UserRelationRepository;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.repository.FakeUserRelationRepository;
import org.maximum0.user.repository.FakeUserRepository;

public class FakeObjectFactory {
    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
