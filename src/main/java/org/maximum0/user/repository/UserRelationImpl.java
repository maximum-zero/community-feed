package org.maximum0.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.maximum0.user.application.interfaces.UserRelationRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.repository.entity.UserEntity;
import org.maximum0.user.repository.entity.UserRelationEntity;
import org.maximum0.user.repository.entity.UserRelationIdEntity;
import org.maximum0.user.repository.jpa.JpaUserRelationRepository;
import org.maximum0.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationImpl implements UserRelationRepository {
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Transactional
    @Override
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Transactional
    @Override
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
