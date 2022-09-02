package no.oyvindis.kafkaproducerclimate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaProducerClimateApplication

fun main(args: Array<String>) {
	runApplication<KafkaProducerClimateApplication>(*args)
}
