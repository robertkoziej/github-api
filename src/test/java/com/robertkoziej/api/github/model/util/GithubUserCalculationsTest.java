package com.robertkoziej.api.github.model.util;

import com.robertkoziej.api.github.model.GithubUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.robertkoziej.api.github.model.util.GithubUserCalculations.getCalculations;
import static org.assertj.core.api.Assertions.assertThat;

public class GithubUserCalculationsTest {

    @Test
    void returns_positive_value() {
        // given
        GithubUser githubUser = new GithubUser();
        githubUser.setFollowers(5);
        githubUser.setPublic_repos(2);

        // when
        BigDecimal calculations = getCalculations(githubUser);

        // then
        assertThat(calculations).isEqualTo(new BigDecimal("4.80"));
    }

    @Test
    void returns_zero() {
        // given
        GithubUser githubUser = new GithubUser();
        githubUser.setFollowers(0);
        githubUser.setPublic_repos(3);

        // when
        BigDecimal calculations = getCalculations(githubUser);

        // then
        assertThat(calculations).isEqualTo(BigDecimal.ZERO);
    }
}
