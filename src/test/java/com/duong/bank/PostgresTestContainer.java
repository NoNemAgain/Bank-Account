package com.duong.bank;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer {
    private static final PostgreSQLContainer<?> container;

    static {
        container = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("bankdb")
                .withUsername("postgres")
                .withPassword("postgres");
        container.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return container;
    }
}
