package com.guysrobot.inventoryservice

import com.guysrobot.inventoryservice.model.Inventory
import com.guysrobot.inventoryservice.repository.InventoryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class InventoryServiceApplication {
    @Bean
    fun loadData(inventoryRepository: InventoryRepository): CommandLineRunner {
        return CommandLineRunner {
            listOf(
                Inventory(
                    skuCode = "iphone_12",
                    quantity = 100
                ),
                Inventory(
                    skuCode = "iphone_13",
                    quantity = 0
                )
            ).forEach {
                inventoryRepository.save(it)
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<InventoryServiceApplication>(*args)
}
