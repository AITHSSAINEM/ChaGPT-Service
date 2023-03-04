package com.izicap.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.izicap.test.Service.GptService;
import com.izicap.test.exceptions.EmptyQuestionExcetion;
import com.izicap.test.exceptions.TokenExpiredException;
import com.izicap.test.model.Choice;
import com.izicap.test.model.GptResponse;

@RunWith(MockitoJUnitRunner.class)
public class GptServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GptService gptService;

    @Before
    public void setUp() {
        // Mock the response from the REST API
        GptResponse response = new GptResponse();
        response.setId("123");
        response.setObject("gpt_response");
        response.setCreated("2022-02-22");
        response.setModel("gpt-2");
        List<Choice> choices = new ArrayList<>();
        Choice choice = new Choice();
        choice.setText("Hello world!");
        choices.add(choice);
        response.setChoices(choices);
        ResponseEntity<GptResponse> entity = new ResponseEntity<>(response, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), anyString(), GptResponse.class)).thenReturn(entity);
    }

    @Test
    public void testSendToGpt() throws IOException, EmptyQuestionExcetion, TokenExpiredException {
        // Call the method being tested
        String message = "Hello";
        GptResponse response = gptService.sendToGpt(message);

        // Verify the response
        assertEquals("123", response.getId());
        assertEquals("gpt_response", response.getObject());
        assertEquals("2022-02-22", response.getCreated());
        assertEquals("gpt-2", response.getModel());
        assertEquals(1, response.getChoices().size());
        assertEquals("Hello world!", response.getChoices().get(0).getText());
    }
}
