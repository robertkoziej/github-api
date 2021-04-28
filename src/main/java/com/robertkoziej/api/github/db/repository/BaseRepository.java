package com.robertkoziej.api.github.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    protected void persist(T object) {
        entityManager.persist(object);
    }

    protected void merge(T object) {
        entityManager.merge(object);
    }

    protected JPAQueryFactory queryFactory() {
        if (queryFactory == null) {
            queryFactory = new JPAQueryFactory(entityManager);
        }
        return queryFactory;
    }
}
