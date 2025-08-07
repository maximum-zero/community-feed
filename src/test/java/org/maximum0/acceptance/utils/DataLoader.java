package org.maximum0.acceptance.utils;

import static org.maximum0.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.maximum0.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    @PersistenceContext
    private EntityManager entityManager;

    public void load() {
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto("test user", "");
        createUser(createUserRequestDto);
        createUser(createUserRequestDto);
        createUser(createUserRequestDto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM tb_email_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified FROM EmailVerificationEntity WHERE email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}
