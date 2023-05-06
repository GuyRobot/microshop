package com.guysrobot.micro_product

import com.guysrobot.micro_product.dto.ProductRequest
import com.guysrobot.micro_product.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.math.BigDecimal


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MicroProductApplicationTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var springMvcJacksonConverter: MappingJackson2HttpMessageConverter

    @Autowired
    private lateinit var productRepository: ProductRepository

    private val objectMapper
        get() = springMvcJacksonConverter.objectMapper

    companion object {
        private val productRequest =
            ProductRequest(name = "IPhone 12", description = "iphone 12", price = BigDecimal(1000))

        @Container
        private val mongodbContainer = MongoDBContainer("mongo:latest")
    }

    init {
        mongodbContainer.start()
        System.setProperty("spring.data.mongodb.uri", mongodbContainer.replicaSetUrl)
    }

    @Test
    fun shouldCreateProduct() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productRequest))
        ).andExpect(status().isCreated)

        Assertions.assertEquals(
            productRepository.findAll().size, 1
        )
    }

}
