package com.joelmaciel.libraryproducer.api.controllers;

import com.joelmaciel.libraryproducer.domain.events.LibraryEvent;
import com.joelmaciel.libraryproducer.utils.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryEventsControllerIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Given a POST request is made to create a library event with event data, then the event should be created successfully")
    void postLibraryEvent() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<LibraryEvent> httpEntity =
                new HttpEntity<>(TestUtil.libraryEventRecord(), httpHeaders);

        ResponseEntity<LibraryEvent> responseEntity = restTemplate
                .exchange("/v1/libraryevent", HttpMethod.POST,
                        httpEntity, LibraryEvent.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        LibraryEvent createdEvent = responseEntity.getBody();

        assertNotNull(createdEvent);
        assertNull(createdEvent.libraryEventId());
        assertEquals(createdEvent.libraryEventType(), responseEntity.getBody().libraryEventType());
        assertEquals(createdEvent.book().bookId(), responseEntity.getBody().book().bookId());

    }

}