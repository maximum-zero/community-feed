package org.maximum0.auth.ui;

import lombok.RequiredArgsConstructor;
import org.maximum0.auth.application.AuthService;
import org.maximum0.auth.application.dto.LoginRequestDto;
import org.maximum0.auth.application.dto.UserAccessTokenResponseDto;
import org.maximum0.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(authService.login(dto));
    }

}
