package org.maximum0.user.application;

import java.util.IllformedLocaleException;
import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInformation;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInformation userInformation = new UserInformation(dto.userName(), dto.profileImageUrl());
        User user = new User(null, userInformation);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(IllformedLocaleException::new);
    }

}
