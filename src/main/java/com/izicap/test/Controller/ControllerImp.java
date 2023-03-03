package com.izicap.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.izicap.test.Service.GptService;
import com.izicap.test.exceptions.EmptyQuestionExcetion;
import com.izicap.test.model.GptResponse;

import java.io.IOException;

@RestController
public class ControllerImp{
	
    @Autowired
    public GptService gptService;
    
    
    @GetMapping("/send")
    public GptResponse send(@RequestParam("question") String question) throws IOException, EmptyQuestionExcetion {
        return gptService.sendToGpt(question);
    }
}
