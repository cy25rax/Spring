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
        return readerRepository.getReferenceById(id);
    }

    public void deleteReader(Long id) {
         readerRepository.deleteById(id);
    }

    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Issue> getAllIssueByReaderId(Long id) {
        return issueRepository.getAllIssueByReaderId(id);
    }

    public List<Reader> getAllReaders() {
        System.out.println(readerRepository.findAll());
        return readerRepository.findAll();
    }

    public List<ReadersDetails> getAllBooksNames(Long id) {
        return getAllIssueByReaderId(id).stream()
                .map(x -> new ReadersDetails(
                        bookRepository.getReferenceById(x.getBookId()),
                        x))
                .collect(Collectors.toList());
    }
}
