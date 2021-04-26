package com.robertkoziej.api.github;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GithubApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GithubApplication.class, GithubApplicationConfiguration.class)
                .run(args);
    }
}
