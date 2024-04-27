package com.joelmaciel.libraryproducer.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joelmaciel.libraryproducer.producer.LibraryEventsProducer;
import com.joelmaciel.libraryproducer.utils.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class LibraryEventsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    LibraryEventsProducer libraryEventsProducer;

    @Test
    @DisplayName("Given a POST request library, When event creation, Then expect successful event creation")
    void postLibraryEvent() throws Exception {
        String jsonConverted = objectMapper.writeValueAsString(TestUtil.libraryEventRecord());

        mockMvc
                .perform(post("/v1/libraryevent")
                        .content(jsonConverted)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Given invalid values, When making POST library event, Then expect 4xx client error")
    void postLibraryEvent_invalidValues() throws Exception {
        String jsonConverted = objectMapper.writeValueAsString(TestUtil.libraryEventRecordWithInvalidBook());

        String expectedErrorMessage = "book.bookId - must not be null, book.bookName - must not be blank";

        mockMvc
                .perform(post("/v1/libraryevent")
                        .content(jsonConverted)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(expectedErrorMessage));
    }
}