package org.maximum0.acceptance.utils;

import static org.maximum0.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

import org.junit.jupiter.api.BeforeEach;
import org.maximum0.auth.application.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader loader;

    @BeforeEach
    public void init() {
        cleanUp.execute();
        loader.load();
    }

    protected void cleanUp() {
        cleanUp.execute();
    }

    protected String getEmailToken(String email) {
        return loader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email) {
        return loader.isEmailVerified(email);
    }

    protected Long getUserId(String email) {
        return loader.getUserId(email);
    }

    protected void createUser(String email) {
        loader.createUser(email);
    }

    protected String login(String email) {
        return requestLoginGetToken(new LoginRequestDto(email, "1234"));
    }

}
