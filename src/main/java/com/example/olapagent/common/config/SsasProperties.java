package com.example.olapagent.common.config;

import com.example.olapagent.domain.enumtype.ModelType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ssas")
public class SsasProperties {

    private ModelType modelType;
    private String datasource;
    private String catalog;
    private int timeoutSeconds;
    private String username;
    private String password;
}