package org.maximum0.user.application;

import org.maximum0.user.application.dto.CreateUserRequestDto;
import org.maximum0.user.application.dto.GetUserResponseDto;
import org.maximum0.user.application.interfaces.UserRepository;
import org.maximum0.user.domain.User;
import org.maximum0.user.domain.UserInformation;
import org.springframework.stereotype.Service;

@Service
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

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

}
