package org.maximum0.auth.repository;

import lombok.RequiredArgsConstructor;
import org.maximum0.auth.application.interfaces.UserAuthRepository;
import org.maximum0.auth.domain.UserAuth;
import org.maximum0.auth.repository.entity.UserAuthEntity;
import org.maximum0.auth.repository.jpa.JpaUserAuthRepository;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {
    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

    @Transactional
    @Override
    public UserAuth loginUser(String email, String password) throws IllegalArgumentException {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("Invalid Password");
        }

        userAuthEntity.updateLastLoginAt();
        return userAuth;
    }
}
