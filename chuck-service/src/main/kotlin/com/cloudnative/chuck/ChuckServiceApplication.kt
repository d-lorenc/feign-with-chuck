package com.cloudnative.chuck

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
class ChuckServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(ChuckServiceApplication::class.java, *args)
}

@RestController
class ChuckController(val chuckRepoClient: ChuckRepoClient) {
    @GetMapping("/joke")
    fun joke() = chuckRepoClient.joke()
}

@FeignClient(
        name = "chuck-norris",
        url = "http://chuck-repo.cfapps.io",
        fallback = FakeJoke::class
)
interface ChuckRepoClient {
    @RequestMapping("/jokes/random")
    fun joke(): String
}

@Component
class FakeJoke : ChuckRepoClient {
    override fun joke() = "Bad joke!"
}
