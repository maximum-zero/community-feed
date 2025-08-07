package org.maximum0.auth.domain;

public class Password {
    private final String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createEncryptPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is Not Empty");
        }
        return new Password(SHA256.encrypt(password));
    }

    public boolean matchPassword(String password) {
        return encryptedPassword.matches(SHA256.encrypt(password));
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

}
