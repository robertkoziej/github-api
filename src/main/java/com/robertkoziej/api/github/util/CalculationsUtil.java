package com.robertkoziej.api.github.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationsUtil {

    public static BigDecimal getCalculations(int followers, int publicRepos) {
        if (followers == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(6)
                .setScale(2, RoundingMode.HALF_DOWN)
                .divide(new BigDecimal(followers), RoundingMode.HALF_DOWN)
                .multiply(new BigDecimal(2 + publicRepos));
    }
}
