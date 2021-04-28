package com.robertkoziej.api.github.service;

import com.robertkoziej.api.github.model.GithubUser;
import com.robertkoziej.api.github.service.util.Requester;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class GithubService {

    private final RestTemplate restTemplate;

    public GithubUser getGithubUser(String username) {
        return getGithubUserRequester(username)
                .get();
    }

    private Requester<GithubUser> getGithubUserRequester(String login) {
        return new Requester<>(restTemplate, "https://api.github.com/users/{username}", GithubUser.class)
                .addPathVariable(login);
    }
}
