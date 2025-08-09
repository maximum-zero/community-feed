package org.maximum0.auth.domain;

import lombok.Getter;

public class UserAuth {
    private final Email email;
    private final Password password;
    private final UserRole userRole;
    @Getter
    private Long userId;

    public UserAuth(String email, String password, String role) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = UserRole.valueOf(role);
    }

    public UserAuth(String email, String password, String role, Long userId) {
        this.email = Email.createEmail(email);
        this.password = Password.createPassword(password);
        this.userRole = UserRole.valueOf(role);
        this.userId = userId;
    }

    public String getEmail() {
        return this.email.getValue();
    }

    public String getPassword() {
        return this.password.getEncryptedPassword();
    }

    public String getUserRole() {
        return this.userRole.name();
    }

    public boolean matchPassword(String password) {
        return this.password.matchPassword(password);
    }
}
