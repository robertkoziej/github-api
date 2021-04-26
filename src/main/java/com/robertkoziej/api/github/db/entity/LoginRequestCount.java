package com.robertkoziej.api.github.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_request_count", schema = "public")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestCount {

    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private int requestCount;
}