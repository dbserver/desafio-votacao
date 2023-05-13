package com.db.polling;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgreSQLContainerConfiguration {

    private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:13.4");

    private static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    static {
        CONTAINER.start();
    }

    public static PostgreSQLContainer<?> getContainer() {
        return CONTAINER;
    }
}