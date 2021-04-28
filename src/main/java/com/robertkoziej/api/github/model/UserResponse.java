package com.robertkoziej.api.github.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public final class UserResponse {
    private final long id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final String createdAt;
    private final BigDecimal calculations;
}
