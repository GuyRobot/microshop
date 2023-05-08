package com.guysrobot.inventoryservice.controller

import com.guysrobot.inventoryservice.dto.InventoryResponse
import com.guysrobot.inventoryservice.service.InventoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/inventories")
class InventoryController(
    private val inventoryService: InventoryService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{sku-code}")
    fun checkIsInStock(@PathVariable("sku-code") skuCode: String): Boolean {
        return inventoryService.checkIsInStock(skuCode)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun checkInStock(@RequestParam skuCodes: List<String>): List<InventoryResponse> {
        return inventoryService.checkInStock(skuCodes)
    }
}