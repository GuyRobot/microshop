package com.guysrobot.micro_product.repository

import com.guysrobot.micro_product.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String> {

}