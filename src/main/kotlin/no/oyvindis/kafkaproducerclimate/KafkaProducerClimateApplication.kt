package no.oyvindis.kafkaproducerclimate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
class KafkaProducerClimateApplication

fun main(args: Array<String>) {
	runApplication<KafkaProducerClimateApplication>(*args)
}
