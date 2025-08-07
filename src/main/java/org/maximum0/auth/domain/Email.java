package org.maximum0.auth.domain;

import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class Email {
    private static final String EMAIL_PATTEN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTEN);

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email createEmail(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email is not Valid");
        }

        if (isValidEmail(value)) {
            throw new IllegalArgumentException("Email is not Valid");
        }

        return new Email(value);
    }

    private static boolean isValidEmail(String email) {
        return !pattern.matcher(email).matches();
    }

}
