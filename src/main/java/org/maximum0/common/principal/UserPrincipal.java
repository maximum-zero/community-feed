package org.maximum0.common.principal;

import lombok.Getter;
import org.maximum0.auth.domain.UserRole;

@Getter
public class UserPrincipal {
    private Long userId;
    private UserRole userRole;

    public UserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(role);
    }
}
