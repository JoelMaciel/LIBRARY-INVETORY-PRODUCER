package com.joelmaciel.libraryproducer.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joelmaciel.libraryproducer.domain.events.LibraryEvent;
import com.joelmaciel.libraryproducer.producer.LibraryEventsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class LibraryEventsController {

    private final LibraryEventsProducer libraryEventsProducer;

    public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
        this.libraryEventsProducer = libraryEventsProducer;
    }

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        log.info("libraryEvent : {}", libraryEvent);
        // libraryEventsProducer.sendLibraryEvent(libraryEvent);
        //  libraryEventsProducer.sendLibraryEventApproachTwo(libraryEvent);
        libraryEventsProducer.sendLibraryEventApproachThree(libraryEvent);
        log.info("After Sending library event : ");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
