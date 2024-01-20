package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;


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
}
