package com.cloudnative.chuck

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
class ChuckRepoApplication

fun main(args: Array<String>) {
    SpringApplication.run(ChuckRepoApplication::class.java, *args)
}

val random = Random()
val jokes = arrayOf(
        "Chuck Norris can kill two stones with one bird.",
        "Chuck Norris counted to infinity. Twice.",
        "Chuck Norris can hear sign language."
)

@RestController
class ChuckController {
    @GetMapping("/jokes/random")
    fun joke() = jokes[random.nextInt(jokes.size)]
}
