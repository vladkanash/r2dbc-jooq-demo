package me.vladkanash.r2dbcjooq.config

import io.r2dbc.spi.ConnectionFactory
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfig(
    private val connectionFactory: ConnectionFactory
) {

    @Bean
    fun dslContext(): DSLContext = DSL.using(connectionFactory)
}
