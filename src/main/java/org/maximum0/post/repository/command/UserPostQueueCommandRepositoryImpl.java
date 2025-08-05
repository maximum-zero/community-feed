package org.maximum0.post.repository.command;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.maximum0.post.repository.entity.post.PostEntity;
import org.maximum0.post.repository.entity.post.UserPostQueueEntity;
import org.maximum0.post.repository.jpa.JpaPostRepository;
import org.maximum0.post.repository.jpa.JpaUserPostQueueRepository;
import org.maximum0.user.repository.entity.UserEntity;
import org.maximum0.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Transactional
    @Override
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntityList = followersIds.stream()
                .map(userId -> new UserPostQueueEntity(
                        userId, postEntity.getId(), userEntity.getId()
                ))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Transactional
    @Override
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        List<UserPostQueueEntity> userPostQueueEntityList = postIds.stream()
                .map(postId -> new UserPostQueueEntity(
                        userId, postId, targetId
                ))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Transactional
    @Override
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
