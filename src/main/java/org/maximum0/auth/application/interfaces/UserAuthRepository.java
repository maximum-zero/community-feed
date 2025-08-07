package org.maximum0.auth.application.interfaces;

import org.maximum0.auth.domain.UserAuth;
import org.maximum0.user.domain.User;

public interface UserAuthRepository {
    UserAuth registerUser(UserAuth userAuth, User user);
}
