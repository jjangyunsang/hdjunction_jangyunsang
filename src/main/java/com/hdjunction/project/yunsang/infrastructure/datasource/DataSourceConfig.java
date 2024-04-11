package com.hdjunction.project.yunsang.infrastructure.datasource;

import com.hdjunction.project.yunsang.global.util.ConstantUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSource 작성
 * - querydsl Singleton instance 로 사용가능
 * - master / slave 분리하여 자동으로 적용될 수 있도록 확장가능
 * - 같은내용 복사하여 prefix 추가변경하여 multi datasource 작성가능
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.hdjunction.project.yunsang.domain.hospital.repository"
        , entityManagerFactoryRef = "entityManagerFactory"
        , transactionManagerRef = "transactionManager"
)
@EnableJpaAuditing // 생성일, 수정일을 자동으로 사용할 수 있음
public class DataSourceConfig {
    private final DataSourceProperty dataSourceProperty;

    @Autowired
    public DataSourceConfig(DataSourceProperty dataSourceProperty) {
        this.dataSourceProperty = dataSourceProperty;
    }

    private DataSource createDatasource(DataSourceProperty.Base dataSourceProperty) {
        HikariDataSource hikariDataSource = (HikariDataSource) DataSourceBuilder.create()
                .driverClassName(dataSourceProperty.getDriverClassName())
                .url(dataSourceProperty.getUrl())
                .username(dataSourceProperty.getUserName())
                .password(dataSourceProperty.getPassword())
                .build();

        hikariDataSource.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        return hikariDataSource;
    }
    @Bean("masterDataSource")
    public DataSource masterDataSource() {
        return createDatasource(dataSourceProperty.getMaster());
    }

    @Bean("slaveDataSource")
    public DataSource slaveDataSource() {
        return createDatasource(dataSourceProperty.getSlave());
    }

    @DependsOn("masterDataSource")
    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("masterDataSource") DataSource masterDataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(ConstantUtil.SCHEMA_LOCATIONS));
        populator.addScript(new ClassPathResource(ConstantUtil.DATA_LOCATIONS));

        initializer.setDataSource(masterDataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @DependsOn("masterDataSource")
    @Bean
    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource) {
        DataSource slaveDataSource = createDatasource(dataSourceProperty.getSlave());

        HikariDataSource hikariSlaveDataSource = (HikariDataSource) slaveDataSource;
        hikariSlaveDataSource.setReadOnly(true);

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(ConstantUtil.MASTER, masterDataSource);
        dataSourceMap.put(ConstantUtil.SLAVE, slaveDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(slaveDataSource);

        return routingDataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        AbstractJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setGenerateDdl(false);

        return jpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            @Qualifier("jpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter) {
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, new HashMap<>(), null);
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();

        props.put("hibernate.show_sql", true);
        props.put("hibernate.format_sql", true);
        props.put("hibernate.query.in_clause_parameter_padding", true);

        return props;
    }

    /**
     * EntityManager 주입하여 사용가능
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("routingDataSource") DataSource dataSource
            , @Qualifier("entityManagerFactoryBuilder") EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .packages("com.hdjunction.project.yunsang.domain")
                .properties(jpaProperties())
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) throws Exception {
        EntityManagerFactory entityManagerFactory = entityManagerFactoryBean.getObject();

        if (ObjectUtils.isEmpty(entityManagerFactory)) {
            throw new Exception("EntityManagerFactory is null");
        }

        return new JpaTransactionManager(entityManagerFactory);
    }
}
