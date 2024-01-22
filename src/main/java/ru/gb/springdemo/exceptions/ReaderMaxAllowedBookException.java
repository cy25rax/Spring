package ru.gb.springdemo.exceptions;

/**
 * Ошибка формируемая если у читателя уже есть книга на руках
 */
public class ReaderMaxAllowedBookException extends RuntimeException {
    public ReaderMaxAllowedBookException(String e) {
        super(e);
    }
}
