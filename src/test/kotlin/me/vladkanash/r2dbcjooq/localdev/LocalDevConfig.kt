package me.vladkanash.r2dbcjooq.localdev

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration(proxyBeanMethods = false)
internal class LocalDevTestContainersConfig {

    @Bean
    @ServiceConnection
    fun postgresContainer() = PostgreSQLContainer("postgres:16.1")
}
