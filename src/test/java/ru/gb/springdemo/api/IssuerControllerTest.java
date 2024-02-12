package ru.gb.springdemo.api;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.dto.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

@SpringBootTest
        (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IssuerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Long issueTestCloseId;

    @Test
    @Order(1)
    void issueBook() {
        Reader testReader = new Reader();
        testReader.setName("testReader");
        testReader.setPassword("testPass");
        testReader.setMaxBookCount(2);

        Long testReaderId = readerRepository.save(testReader).getId();

        assert testReaderId != null;

        IssueRequest testRequest = new IssueRequest();
        testRequest.setReaderId(testReaderId);
        testRequest.setBookId(1);

        Issue responseBody = webTestClient.post()
                .uri("/issue")
                .bodyValue(testRequest)
                .exchange()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        issueTestCloseId = responseBody.getId();
        Assertions.assertEquals(responseBody.getBookId(), 1);
        Assertions.assertEquals(responseBody.getReaderId(), testReaderId);
        Assertions.assertNull(responseBody.getReturned_at());

        System.out.println(issueTestCloseId); // ТУТ НЕ NULL
    }

    @Test
    @Order(2)
    void closeIssue() {
        System.out.println(issueTestCloseId); // A ТУТ NULL
        Issue responseBody = webTestClient.post()
                .uri("/issue/" + 2)
//                .uri("/issue/" + issueTestCloseId)
                .exchange()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        Assertions.assertNotNull(responseBody.getReturned_at());
    }

    @Test
    @Order(3)
    void getIssue() {
        Long testBookId = jdbcTemplate.queryForObject("select book_id from issues where id = 1", Long.class);

        Issue responseBody = webTestClient.get()
                .uri("/issue/1")
                .exchange()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        Assertions.assertEquals(responseBody.getBookId(), testBookId);
    }

}