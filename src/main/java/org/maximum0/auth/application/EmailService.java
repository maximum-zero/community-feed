package org.maximum0.auth.application;

import lombok.RequiredArgsConstructor;
import org.maximum0.auth.application.dto.SendEmailRequestDto;
import org.maximum0.auth.application.interfaces.EmailSendRepository;
import org.maximum0.auth.application.interfaces.EmailVerificationRepository;
import org.maximum0.auth.domain.Email;
import org.maximum0.auth.domain.RandomTokenGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String email, String token) {
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue, token);
    }
}
