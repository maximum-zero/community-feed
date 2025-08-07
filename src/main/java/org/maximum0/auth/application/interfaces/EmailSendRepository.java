package org.maximum0.auth.application.interfaces;

import org.maximum0.auth.domain.Email;

public interface EmailSendRepository {
    void sendEmail(Email email, String randomToken);
}
