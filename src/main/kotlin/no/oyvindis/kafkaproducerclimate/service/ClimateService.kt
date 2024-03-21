package no.oyvindis.kafkaproducerclimate.service

import no.oyvindis.kafkaproducerclimate.entities.Sensor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

private val logger: Logger = LoggerFactory.getLogger(ClimateService::class.java)

class AirthingsResponse {
    val data: Sensor? = null
}

@Service
class ClimateService(
    private val webClient: WebClient,
    @Autowired
    val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.sensor}")
    val topic: String,
) {
    @Scheduled(fixedDelay = 21600000)
    fun postReadingFromAirthings() {
        try {
            logger.debug("postReadingFromAirthings")
            val data = webClient
                .get()
                .uri("https://ext-api.airthings.com/v1/devices/2960009475/latest-samples")
                .attributes(
                    ServerOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId("airthings")
                )
                .retrieve()
                .bodyToMono<AirthingsResponse>()
            val responseStr = data.block()

            val sensorReading: Sensor? = responseStr?.data
            sensorReading?.location = "2960009475"
            sensorReading?.let {
                val message: Message<Sensor?> = MessageBuilder
                    .withPayload(sensorReading)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build()
                kafkaTemplate.send(message)
                logger.info("Message sent with success")
            }
        } catch (e: Exception) {
            logger.error("Exception: {}", e)
        }
    }
}