package org.maximum0.post.application;

import org.maximum0.post.domain.Post;
import org.maximum0.post.application.dto.CreatePostRequestDto;
import org.maximum0.post.application.dto.LikeRequestDto;
import org.maximum0.post.application.dto.UpdatePostRequestDto;
import org.maximum0.post.application.interfaces.LikeRepository;
import org.maximum0.post.application.interfaces.PostRepository;
import org.maximum0.post.domain.content.Content;
import org.maximum0.post.domain.content.PostContent;
import org.maximum0.user.application.UserService;
import org.maximum0.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.authorId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        User author = userService.getUser(dto.authorId());
        Post post = getPost(dto.id());

        post.updatePost(author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(dto.id());

        if (likeRepository.isAlreadyLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(dto.id());

        if (likeRepository.isAlreadyLike(post, user)) {
            post.unlike(user);
            likeRepository.unlike(post, user);
        }
    }

}
