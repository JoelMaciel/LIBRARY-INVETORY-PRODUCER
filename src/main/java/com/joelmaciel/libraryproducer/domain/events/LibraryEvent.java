package com.joelmaciel.libraryproducer.domain.events;

import com.joelmaciel.libraryproducer.domain.dtos.Book;
import com.joelmaciel.libraryproducer.domain.enums.LibraryEventType;

public record LibraryEvent(
        Integer libraryEventId,
        LibraryEventType libraryEventType,
        Book book
) {
}
