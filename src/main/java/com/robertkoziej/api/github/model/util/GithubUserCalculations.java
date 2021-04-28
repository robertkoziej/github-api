package com.robertkoziej.api.github.model.util;

import com.robertkoziej.api.github.model.GithubUser;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GithubUserCalculations {

    public static BigDecimal getCalculations(GithubUser githubUser) {
        int followers = githubUser.getFollowers();
        int publicRepos = githubUser.getPublicRepos();
        if (followers == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(6)
                .setScale(2, RoundingMode.HALF_DOWN)
                .divide(new BigDecimal(followers), RoundingMode.HALF_DOWN)
                .multiply(new BigDecimal(2 + publicRepos));
    }
}
