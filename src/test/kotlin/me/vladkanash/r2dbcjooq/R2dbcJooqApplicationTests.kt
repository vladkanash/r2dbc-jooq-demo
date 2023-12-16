package me.vladkanash.r2dbcjooq
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class R2dbcJooqApplicationTests {

	@Test
	fun contextLoads() {
	}

	companion object {
		@Container
		@JvmStatic
		@ServiceConnection
		var postgres = PostgreSQLContainer("postgres:16.1")
	}
}
