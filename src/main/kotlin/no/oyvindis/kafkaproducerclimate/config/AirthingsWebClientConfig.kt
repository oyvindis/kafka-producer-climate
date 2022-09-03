package no.oyvindis.kafkaproducerclimate.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient


private val logger: Logger = LoggerFactory.getLogger(AirthingsWebClientConfig::class.java)

@Configuration
open class AirthingsWebClientConfig {
    @Bean
    open fun webClient(clientRegistrationRepository: ClientRegistrationRepository): WebClient? {
        val registrationRepository =
            InMemoryReactiveClientRegistrationRepository(clientRegistrationRepository.findByRegistrationId("airthings"))
        val clientService = InMemoryReactiveOAuth2AuthorizedClientService(registrationRepository)
        val clientManager =
            AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(registrationRepository, clientService)
        return WebClient.builder()
            .filter(ServerOAuth2AuthorizedClientExchangeFilterFunction(clientManager))
            .build()
    }
}
