package pa.simpleapitest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["pa.simpleapitest.biz", "pa.simpleapitest.rest"])
open class BookApiApplication

fun main(args: Array<String>) {
    runApplication<BookApiApplication>(*args)
}