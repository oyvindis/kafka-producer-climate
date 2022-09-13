package no.oyvindis.kafkaproducerclimate.entities.serializer

import com.fasterxml.jackson.databind.ObjectMapper
import no.oyvindis.kafkaproducerclimate.entities.Sensor
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class SensorSerializer : Serializer<Sensor> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: Sensor?): ByteArray? {
        log.info("Serializing...")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Sensor to ByteArray[]")
        )
    }
}