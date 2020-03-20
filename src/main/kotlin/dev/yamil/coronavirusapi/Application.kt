package dev.yamil.coronavirusapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.Duration.ofSeconds

@SpringBootApplication
class CoronaVirusApiApplication {
    @Bean
    fun providesRestTemplate(): RestTemplate = RestTemplateBuilder()
            .setConnectTimeout(ofSeconds(10))
            .setReadTimeout(ofSeconds(10))
            .build()

}

fun main(args: Array<String>) {
    runApplication<CoronaVirusApiApplication>(*args)
}




