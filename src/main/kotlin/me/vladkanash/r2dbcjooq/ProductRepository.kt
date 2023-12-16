package me.vladkanash.r2dbcjooq

import kotlinx.coroutines.reactive.awaitSingle
import me.vladkanash.r2dbcjooq.entity.Product
import org.jooq.DSLContext
import org.jooq.kotlin.coroutines.transactionCoroutine
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class ProductRepository(
    private val dsl: DSLContext
) {

    suspend fun getProducts(): List<Product> =
        dsl.transactionCoroutine { config ->
            Flux.from(config.dsl().selectFrom(PRODUCTS))
                .map { it.into(Product::class.java) }
                .collectList()
                .awaitSingle()
        }

    companion object {
        private const val PRODUCTS = "products"
    }
}
