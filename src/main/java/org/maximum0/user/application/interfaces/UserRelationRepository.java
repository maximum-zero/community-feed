package org.maximum0.user.application.interfaces;

import org.maximum0.user.domain.User;

public interface UserRelationRepository {
    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);
}
