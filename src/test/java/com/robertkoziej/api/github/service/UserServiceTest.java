package com.robertkoziej.api.github.service;

import com.robertkoziej.api.github.db.entity.LoginRequestCount;
import com.robertkoziej.api.github.model.GithubUser;
import com.robertkoziej.api.github.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final int ID = 1;
    private static final String LOGIN = "LOGIN";
    private static final String NAME = "NAME";
    private static final String TYPE = "USER";
    private static final String AVATAR_URL = "https://avatars.githubusercontent.com/u/123";
    private static final String CREATED_AT = "2014-04-12T10:51:45Z";
    private static final int FOLLOWERS = 2;
    private static final int PUBLIC_REPOS = 3;

    private static final GithubUser GITHUB_USER_MOCK = mockGithubUser();
    private static final LoginRequestCount LOGIN_REQUEST_COUNT_MOCK = mockLoginRequestCount();

    @Mock
    private LoginRequestCountService loginRequestCountService;
    @Mock
    private GithubService githubService;

    @Test
    void should_return_user_by_login() {
        // given
        given(loginRequestCountService.saveLoginRequestCount(eq(LOGIN)))
                .willReturn(LOGIN_REQUEST_COUNT_MOCK);
        given(githubService.getGithubUser(eq(LOGIN)))
                .willReturn(GITHUB_USER_MOCK);
        UserService userService = new UserService(loginRequestCountService, githubService);

        // when
        UserResponse userResponse = userService.getUserByLogin(LOGIN);

        // then
        assertThat(userResponse.getLogin()).isEqualTo(LOGIN);
        assertThat(userResponse.getId()).isEqualTo(ID);
        assertThat(userResponse.getName()).isEqualTo(NAME);
        assertThat(userResponse.getType()).isEqualTo(TYPE);
        assertThat(userResponse.getAvatarUrl()).isEqualTo(AVATAR_URL);
        assertThat(userResponse.getCreatedAt()).isEqualTo(CREATED_AT);
        assertThat(userResponse.getCalculations()).isNotNull();
    }

    private static LoginRequestCount mockLoginRequestCount() {
        return LoginRequestCount.builder()
                .login(LOGIN)
                .requestCount(1)
                .build();
    }

    private static GithubUser mockGithubUser() {
        GithubUser githubUser = new GithubUser();
        githubUser.setId(ID);
        githubUser.setLogin(LOGIN);
        githubUser.setName(NAME);
        githubUser.setType(TYPE);
        githubUser.setAvatar_url(AVATAR_URL);
        githubUser.setCreated_at(CREATED_AT);
        githubUser.setFollowers(FOLLOWERS);
        githubUser.setPublic_repos(PUBLIC_REPOS);
        return githubUser;
    }
}
