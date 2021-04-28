package com.robertkoziej.api.github.service;

import com.robertkoziej.api.github.db.entity.LoginRequestCount;
import com.robertkoziej.api.github.model.GithubUser;
import com.robertkoziej.api.github.model.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.robertkoziej.api.github.model.util.GithubUserCalculations.getCalculations;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final LoginRequestCountService loginRequestCountService;
    private final GithubService githubService;

    public UserResponse getUserByLogin(String login) {
        LoginRequestCount loginRequestCount = loginRequestCountService.saveLoginRequestCount(login);
        log.debug("login={}, requestCount={}", login, loginRequestCount.getRequestCount());

        GithubUser githubUser = githubService.getGithubUser(login);
        log.debug("githubUser={}", githubUser);

        return buildUserResponse(githubUser);
    }

    private UserResponse buildUserResponse(GithubUser githubUser) {
        BigDecimal calculations = getCalculations(githubUser);
        return UserResponse.builder()
                .id(githubUser.getId())
                .login(githubUser.getLogin())
                .name(githubUser.getName())
                .type(githubUser.getType())
                .avatarUrl(githubUser.getAvatarUrl())
                .createdAt(githubUser.getCreatedAt())
                .calculations(calculations)
                .build();
    }
}
