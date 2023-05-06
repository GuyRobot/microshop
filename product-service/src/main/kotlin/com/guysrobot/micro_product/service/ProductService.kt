package com.guysrobot.micro_product.service

import com.guysrobot.micro_product.dto.ProductRequest
import com.guysrobot.micro_product.dto.ProductResponse
import com.guysrobot.micro_product.model.Product
import com.guysrobot.micro_product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun create(productRequest: ProductRequest) {
        productRepository.save(
            Product(
                name = productRequest.name,
                description = productRequest.description,
                price = productRequest.price
            )
        )
    }

    fun findAll(): List<ProductResponse> {
        return productRepository.findAll().map { it.toResponse() }
    }
}