package com.robertkoziej.api.github.service;

import com.robertkoziej.api.github.db.entity.LoginRequestCount;
import com.robertkoziej.api.github.db.repository.LoginRequestCountRepository;
import com.robertkoziej.api.github.model.GithubUser;
import com.robertkoziej.api.github.model.UserResponse;
import com.robertkoziej.api.github.util.CalculationsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final LoginRequestCountRepository loginRequestCountRepository;
    private final GithubService githubService;

    public UserResponse getUserByLogin(String login) {
        LoginRequestCount loginRequestCount = saveLoginRequestCount(login);
        log.debug("login={}, requestCount={}", login, loginRequestCount.getRequestCount());

        GithubUser githubUser = githubService.getGithubUser(login);
        log.debug("githubUser={}", githubUser);

        return buildUserResponse(githubUser);
    }

    private LoginRequestCount saveLoginRequestCount(String login) {
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

    private BigDecimal getCalculations(GithubUser githubUser) {
        int followers = githubUser.getFollowers();
        int publicRepos = githubUser.getPublicRepos();
        return CalculationsUtil.getCalculations(followers, publicRepos);
    }
}
