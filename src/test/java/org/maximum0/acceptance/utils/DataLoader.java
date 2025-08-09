package org.maximum0.acceptance.utils;

import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.maximum0.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.maximum0.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.maximum0.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.maximum0.auth.application.dto.CreateUserAuthRequestDto;
import org.maximum0.auth.application.dto.SendEmailRequestDto;
import org.maximum0.auth.domain.UserRole;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    @PersistenceContext
    private EntityManager entityManager;

    public void load() {
        // User 생성
        for (int i = 0; i < 3; i++) {
            createUser("maximum" + i + "@gmail.com");
        }

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

    public Long getUserId(String email) {
        return entityManager.createQuery("SELECT userId FROM UserAuthEntity WHERE email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void createUser(String email) {
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email, "1234", UserRole.USER.name(), "maximum0", ""));
    }

}
