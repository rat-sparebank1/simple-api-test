package pa.simpleapitest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["pa.simpleapitest.biz", "pa.simpleapitest.rest"])
open class TestApiApplication

fun main(args: Array<String>) {
    runApplication<TestApiApplication>(*args)
}