package com.db.polling.domain.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimeSessionProperties.class)
public class AppConfig {

}
