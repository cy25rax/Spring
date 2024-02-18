package ru.gb.springdemo.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.service.BookService;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class BookControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BookRepository bookRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getBook() {
        Book testBook = new Book();
        testBook.setName("testBook");

        when(bookRepository.getReferenceById(1L)).thenReturn(testBook);


        Book responseBody = webTestClient.get()
                .uri("/book/1")
                .exchange()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        Assertions.assertEquals(responseBody.getName(), testBook.getName());
    }

    @Test
    void createBook() {
        Book testBook = new Book();
        testBook.setName("testBook");

        Book returnBook = new Book();
        returnBook.setName("testBook");
        returnBook.setId(99L);

        when(bookRepository.save(testBook)).thenReturn(returnBook);

        Book responseBook = webTestClient.post()
                .uri("/book")
                .bodyValue(testBook)
                .exchange()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBook);

        Assertions.assertEquals(responseBook.getName(), returnBook.getName());
        Assertions.assertEquals(responseBook.getId(), returnBook.getId());
    }

    @Test
    void deleteBook() {
        Book testBook = new Book();
        testBook.setName("testBook");

        bookRepository.save(testBook);

        Long testBookId = jdbcTemplate.queryForObject("select id from books where name like 'testBook'", Long.class);

        Assertions.assertNotNull(testBookId);

        webTestClient.delete()
                .uri("/book/" + testBookId)
                .exchange()
                .expectBody(Void.class);

        Long deletedTestBookId = jdbcTemplate.queryForObject("select id from books where name like 'testBook'", Long.class);

        Assertions.assertNull(deletedTestBookId);
    }
}