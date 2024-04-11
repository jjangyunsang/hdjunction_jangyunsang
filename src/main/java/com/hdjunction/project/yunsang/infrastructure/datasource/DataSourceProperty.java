package com.hdjunction.project.yunsang.infrastructure.datasource;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperty {

    private Master master;
    private Slave slave;

    @Getter
    @Setter
    public static class Base extends HikariConfig {
        private String url;
        private String driverClassName;
        private String userName;
        private String password;
    }

    @Getter
    @Setter
    public static class Master extends Base {}

    @Getter
    @Setter
    public static class Slave extends Base {}
}
