package me.vladkanash.r2dbcjooq.controller

import me.vladkanash.r2dbcjooq.ProductRepository
import me.vladkanash.r2dbcjooq.entity.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val productRepository: ProductRepository,
) {

    @GetMapping("/products")
    suspend fun getProducts(): List<Product> = productRepository.getProducts()
}
