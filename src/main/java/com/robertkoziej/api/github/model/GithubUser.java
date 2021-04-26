package com.robertkoziej.api.github.model;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class GithubUser {
    private String login;
    private long id;
    private String name;
    private String type;
    private String avatar_url;
    private String created_at;
    private int followers;
    private int public_repos;

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPublicRepos() {
        return public_repos;
    }
}
