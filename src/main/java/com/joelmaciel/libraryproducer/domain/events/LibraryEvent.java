package com.joelmaciel.libraryproducer.domain.events;

import com.joelmaciel.libraryproducer.domain.dtos.Book;
import com.joelmaciel.libraryproducer.domain.enums.LibraryEventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LibraryEvent(
        Integer libraryEventId,
        LibraryEventType libraryEventType,
        @NotNull
        @Valid
        Book book
) {
}
