package com.robertkoziej.api.github.service;

import com.robertkoziej.api.github.db.entity.LoginRequestCount;
import com.robertkoziej.api.github.db.repository.LoginRequestCountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginRequestCountService {

    private final LoginRequestCountRepository loginRequestCountRepository;

    public LoginRequestCount saveLoginRequestCount(String login) {
        Optional<LoginRequestCount> loginRequestCountOptional = loginRequestCountRepository.getLoginRequestCount(login);
        return loginRequestCountOptional
                .map(this::incrementLoginRequestCount)
                .orElseGet(() -> initLoginRequestCount(login));
    }

    private LoginRequestCount incrementLoginRequestCount(LoginRequestCount loginRequestCount) {
        loginRequestCount.setRequestCount(loginRequestCount.getRequestCount() + 1);
        loginRequestCountRepository.update(loginRequestCount);
        return loginRequestCount;
    }

    private LoginRequestCount initLoginRequestCount(String login) {
        LoginRequestCount loginRequestCount = buildLoginRequestCount(login, 1);
        loginRequestCountRepository.insert(loginRequestCount);
        return loginRequestCount;
    }

    private LoginRequestCount buildLoginRequestCount(String login, int requestCount) {
        return LoginRequestCount.builder()
                .login(login)
                .requestCount(requestCount)
                .build();
    }
}
