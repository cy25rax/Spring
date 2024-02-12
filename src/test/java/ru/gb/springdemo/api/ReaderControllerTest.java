package ru.gb.springdemo.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
        (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ReaderControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ReaderRepository readerRepository;

    @MockBean
    IssueRepository issueRepository;

    @Test
    void getAllIssueByReaderId() {
        List<Issue> testIssues = new ArrayList<>(List.of(new Issue(1, 1)));

        when(issueRepository.getAllIssueByReaderId(1L)).thenReturn(testIssues);

        List<Issue> responseBody = webTestClient.get()
                .uri("/reader/1/issue")
                .exchange()
                .expectBody(new ParameterizedTypeReference<List<Issue>>() {
                })
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        Assertions.assertEquals(responseBody.get(0), testIssues.get(0));

    }

    @Test
    void getReader() {
        Reader testReader = new Reader();
        testReader.setName("testReader");

        when(readerRepository.getReferenceById(1L)).thenReturn(testReader);

        Reader responseBody = webTestClient.get()
                .uri("/reader/1")
                .exchange()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();

        assert responseBody != null;
        Assertions.assertEquals(responseBody.getName(), testReader.getName());
    }

    @Test
    void createReader() {
        Reader testReader = new Reader();
        testReader.setName("testReader");

        Reader returnReader = new Reader();
        returnReader.setName("testReader");
        returnReader.setId(99L);

        when(readerRepository.save(testReader)).thenReturn(returnReader);

        Reader responseReader = webTestClient.post()
                .uri("/reader")
                .bodyValue(testReader)
                .exchange()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseReader);

        Assertions.assertEquals(responseReader.getName(), returnReader.getName());
        Assertions.assertEquals(responseReader.getId(), returnReader.getId());
    }
}