package com.hdjunction.project.yunsang.infrastructure.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperty {
    private String url;
    private String driverClassName;
    private String userName;
    private String password;
}
