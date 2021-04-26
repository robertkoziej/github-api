package com.robertkoziej.api.github.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationsUtilTest {

    @Test
    void should_get_calculations_return_positive_value() {
        // given
        int followers = 5;
        int publicRepos = 2;

        // when
        BigDecimal calculations = CalculationsUtil.getCalculations(followers, publicRepos);

        // then
        assertThat(calculations).isEqualTo(new BigDecimal("4.80"));
    }

    @Test
    void should_get_calculations_return_zero() {
        // given
        int followers = 0;
        int publicRepos = 3;

        // when
        BigDecimal calculations = CalculationsUtil.getCalculations(followers, publicRepos);

        // then
        assertThat(calculations).isEqualTo(BigDecimal.ZERO);
    }
}
