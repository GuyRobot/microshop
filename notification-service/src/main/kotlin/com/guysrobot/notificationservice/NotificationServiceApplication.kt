package com.guysrobot.notificationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener

@SpringBootApplication
class NotificationServiceApplication {

    @KafkaListener(id = "notificationTopic")
    fun listen(orderPlacedEvent: OrderPlacedEvent) {

    }
}

fun main(args: Array<String>) {
    runApplication<NotificationServiceApplication>(*args)
}