package org.maximum0.auth.application;

import lombok.RequiredArgsConstructor;
import org.maximum0.auth.application.dto.CreateUserAuthRequestDto;
import org.maximum0.auth.application.interfaces.EmailVerificationRepository;
import org.maximum0.auth.application.interfaces.UserAuthRepository;
import org.maximum0.auth.domain.Email;
import org.maximum0.auth.domain.UserAuth;
import org.maximum0.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository verificationRepository;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());

        if (!verificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("This email not verification");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);
        return userAuth.getUserId();
    }

}
