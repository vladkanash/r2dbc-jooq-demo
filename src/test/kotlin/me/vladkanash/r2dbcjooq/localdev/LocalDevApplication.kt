package me.vladkanash.r2dbcjooq.localdev

import org.springframework.boot.SpringApplication
import me.vladkanash.r2dbcjooq.main as r2dbcDemoApplication

fun main(args: Array<String>) {
    SpringApplication.from(::r2dbcDemoApplication)
        .with(LocalDevTestContainersConfig::class.java)
        .run(*args)
}
