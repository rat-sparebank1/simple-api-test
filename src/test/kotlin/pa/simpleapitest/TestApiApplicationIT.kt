package pa.simpleapitest

import io.restassured.RestAssured
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.path.json.JsonPath
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.web.server.LocalServerPort
import pa.simpleapitest.biz.Book

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestApiApplicationIT  {
    @LocalServerPort
    private val port = 0

    @BeforeAll
    fun setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost/api"
    }

    @Test
    fun `book list`() {
        val response = get(200)
        val jsonPath = JsonPath(response)
        val books = jsonPath.getList("$", Book::class.java)
        assert(books.size == 5)

        val book = books.last()
        assert(book.id == 5L)
        assert(book.title == "Design Patterns")
        assert(book.author == "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides")
    }

    private fun get(httpCode: Int = 200): String {
        return  When {
            get("/books")
        } Then {
            statusCode(httpCode)
        } Extract {
            asString()
        }
    }
}