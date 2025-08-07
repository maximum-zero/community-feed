package org.maximum0.auth.repository;

import org.maximum0.auth.application.interfaces.EmailSendRepository;
import org.maximum0.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String randomToken) {

    }
}
