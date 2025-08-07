package org.maximum0.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.auth.domain.UserAuth;

@Entity
@Table(name = "tb_user_auth")
@NoArgsConstructor
@Getter
public class UserAuthEntity {
    @Id
    private String email;
    private String password;
    private String role;
    private Long userId;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail();
        this.password = userAuth.getPassword();
        this.role = userAuth.getUserRole();
        this.userId = userId;
    }

    public UserAuth toUserAuth() {
        return new UserAuth(email, password, role, userId);
    }

}
