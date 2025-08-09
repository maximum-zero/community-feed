package org.maximum0.common.config;

import java.util.List;
import org.maximum0.auth.domain.TokenProvider;
import org.maximum0.common.principal.AuthPrincipalArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    private final TokenProvider tokenProvider;

    public AuthConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addArgumentResolvers(List arugmentResolvers) {
        arugmentResolvers.add(new AuthPrincipalArgumentResolver(tokenProvider));
    }

}
