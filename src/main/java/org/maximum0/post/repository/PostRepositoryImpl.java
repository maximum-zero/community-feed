package org.maximum0.post.repository;

import lombok.RequiredArgsConstructor;
import org.maximum0.post.application.interfaces.PostRepository;
import org.maximum0.post.domain.Post;
import org.maximum0.post.repository.command.UserPostQueueCommandRepository;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.maximum0.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Transactional
    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            jpaPostRepository.updatePost(post);
            return post;
        }
        PostEntity postEntity = jpaPostRepository.save(new PostEntity(post));
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return postEntity.toPost();
    }
}
