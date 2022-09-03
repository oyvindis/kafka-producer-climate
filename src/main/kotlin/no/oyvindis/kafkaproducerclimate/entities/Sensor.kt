package no.oyvindis.kafkaproducerclimate.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class Sensor(
    @JsonProperty("temperature")
    val temperature: String,
    @JsonProperty("humidity")
    val humidity: String?
)