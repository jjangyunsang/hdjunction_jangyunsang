package com.hdjunction.project.yunsang.infrastructure.datasource;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(@Qualifier("entityManagerFactory") EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
