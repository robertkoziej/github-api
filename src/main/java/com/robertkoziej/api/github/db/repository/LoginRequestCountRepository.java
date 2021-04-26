package com.robertkoziej.api.github.db.repository;

import com.robertkoziej.api.github.db.entity.LoginRequestCount;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.robertkoziej.api.github.db.entity.QLoginRequestCount.loginRequestCount;

@Repository
public class LoginRequestCountRepository extends BaseRepository<LoginRequestCount> {

    @Transactional
    public void insert(LoginRequestCount loginRequestCount) {
        persist(loginRequestCount);
    }

    @Transactional
    public void update(LoginRequestCount loginRequestCount) {
        merge(loginRequestCount);
    }

    public Optional<LoginRequestCount> getLoginRequestCount(String login) {
        return Optional.ofNullable(queryFactory().selectFrom(loginRequestCount)
                .where(loginRequestCount.login.eq(login))
                .fetchFirst());
    }
}