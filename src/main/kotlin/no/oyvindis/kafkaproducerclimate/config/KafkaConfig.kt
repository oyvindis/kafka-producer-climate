package no.oyvindis.kafkaproducerclimate.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.KafkaAdmin

@EnableKafka
@Configuration
class KafkaConsumerConfig (
    @Value("\${kafka.bootstrapAddress}")
    private val servers: String,
    @Value("\${kafka.topics.sensor}")
    private val topic: String
        ) {

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
        return KafkaAdmin(configs)
    }

    @Bean
    fun porduto(): NewTopic {
        return NewTopic(topic, 1, 1.toShort())
    }
}