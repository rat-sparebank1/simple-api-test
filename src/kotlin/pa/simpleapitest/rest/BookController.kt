package pa.simpleapitest.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pa.simpleapitest.biz.Book
import pa.simpleapitest.biz.BookRepository

@RestController
@RequestMapping("/api/books")
class BookController(private val bookRepository: BookRepository) {

    @GetMapping
    fun getAllBooks(): List<Book> = bookRepository.findAll()

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): Book =
        bookRepository.findById(id).orElseThrow { BookNotFoundException(id) }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody book: Book): Book = bookRepository.save(book)

    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody updatedBook: Book): Book {
        return if (bookRepository.existsById(id)) {
            bookRepository.save(updatedBook.copy(id = id))
        } else {
            throw BookNotFoundException(id)
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Long) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id)
        } else {
            throw BookNotFoundException(id)
        }
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class BookNotFoundException(bookId: Long) : RuntimeException("Book with id $bookId not found")
