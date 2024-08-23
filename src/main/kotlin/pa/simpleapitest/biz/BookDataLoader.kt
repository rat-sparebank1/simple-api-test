package pa.simpleapitest.biz

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class BookDataLoader(private val bookRepository: BookRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        // Create a list of books to populate the repository
        val books = listOf(
            Book(title = "Clean Code", author = "Robert C. Martin"),
            Book(title = "Effective Java", author = "Joshua Bloch"),
            Book(title = "The Pragmatic Programmer", author = "Andy Hunt and Dave Thomas"),
            Book(title = "Kotlin in Action", author = "Dmitry Jemerov and Svetlana Isakova"),
            Book(title = "Design Patterns", author = "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides")
        )

        // Save all books to the repository
        bookRepository.saveAll(books)
    }
}