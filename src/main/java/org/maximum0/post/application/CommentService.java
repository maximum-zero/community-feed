package org.maximum0.post.application;

import org.maximum0.post.domain.Post;
import org.maximum0.post.application.dto.CreateCommentRequestDto;
import org.maximum0.post.application.dto.LikeRequestDto;
import org.maximum0.post.application.dto.UpdateCommentRequestDto;
import org.maximum0.post.application.interfaces.CommentRepository;
import org.maximum0.post.application.interfaces.LikeRepository;
import org.maximum0.post.domain.comment.Comment;
import org.maximum0.user.application.UserService;
import org.maximum0.user.domain.User;

public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(
            UserService userService,
            PostService postService,
            CommentRepository commentRepository,
            LikeRepository likeRepository
    ) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.authorId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.id());
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.authorId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.id());

        if (likeRepository.isAlreadyLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.id());

        if (likeRepository.isAlreadyLike(comment, user)) {
            comment.unlike(user);
            likeRepository.unlike(comment, user);
        }
    }

}
