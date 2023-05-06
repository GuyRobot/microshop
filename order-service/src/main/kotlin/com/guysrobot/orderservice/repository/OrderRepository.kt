package com.guysrobot.orderservice.repository

import com.guysrobot.orderservice.model.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long>