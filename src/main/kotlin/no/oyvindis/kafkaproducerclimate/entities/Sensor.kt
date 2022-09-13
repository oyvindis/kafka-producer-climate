package no.oyvindis.kafkaproducerclimate.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class Sensor(
    var location: String?,
    val battery: String? = null,
    val co2: String? = null,
    val humidity: String? = null,
    val pm1: String? = null,
    val pm25: String? = null,
    val pressure: String? = null,
    val temp: String? = null,
    val voc: String? = null
)
