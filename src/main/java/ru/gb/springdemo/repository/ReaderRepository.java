package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    @Value("${application.issue.max-allowed-books:1}")
    private int max;

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("reader 1"),
                new Reader("reader 2")
        ));
        readers.forEach(it ->it.setMaxBookCount(max));
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteReaderById(Long id) {
        Reader removingReader = getReaderById(id);
        if (removingReader != null) {
            readers.remove(removingReader);
            return true;
        }
        return false;
    }

    public boolean createReader(Reader reader) {
        Reader newReader = new Reader(reader.getName());
        readers.add(newReader);
        if (readers.contains(newReader)) {
            return true;
        }
        return false;
    }

    public List<Reader> getAllReaders() {
        return List.copyOf(readers);
    }
}
