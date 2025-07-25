package org.maximum0.user.application.interfaces;

import java.util.Optional;
import org.maximum0.user.domain.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);

}
