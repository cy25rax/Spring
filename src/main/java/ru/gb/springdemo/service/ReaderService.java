package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.dto.ReadersDetails;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;


    public Reader getReader(Long id) {
        return readerRepository.getReaderById(id);
    }

    public boolean deleteReader(Long id) {
        return readerRepository.deleteReaderById(id);
    }

    public boolean createReader(Reader reader) {
        return readerRepository.createReader(reader);
    }

    public List<Issue> getAllIssueByReaderId(Long id) {
        return issueRepository.getAllIssueByReaderId(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

    public List<ReadersDetails> getAllBooksNames(Long id) {
        return getAllIssueByReaderId(id).stream()
                .map(x -> new ReadersDetails(
                        bookRepository.getBookById(x.getBookId()),
                        x))
                .collect(Collectors.toList());
    }
}
