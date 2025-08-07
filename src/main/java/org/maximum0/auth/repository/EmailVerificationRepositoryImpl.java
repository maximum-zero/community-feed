package org.maximum0.auth.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.maximum0.auth.application.interfaces.EmailVerificationRepository;
import org.maximum0.auth.domain.Email;
import org.maximum0.auth.repository.entity.EmailVerificationEntity;
import org.maximum0.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {
    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Transactional
    @Override
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getValue();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailAddress);

        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();

            if (emailVerificationEntity.isVerified()) {
                throw new IllegalArgumentException("The email has already been verified");
            }

            emailVerificationEntity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Transactional
    @Override
    public void verifyEmail(Email email, String token) throws IllegalArgumentException {
        String emailAddress = email.getValue();

        EmailVerificationEntity emailVerificationEntity = jpaEmailVerificationRepository.findByEmail(emailAddress)
                .orElseThrow(() -> new IllegalArgumentException("This email not verification"));

        if (emailVerificationEntity.isVerified()) {
            throw new IllegalArgumentException("This email is already verified");
        }

        if (!emailVerificationEntity.hasSameToken(token)) {
            throw new IllegalArgumentException("Token value is invalid");
        }

        emailVerificationEntity.verify();
    }

}
