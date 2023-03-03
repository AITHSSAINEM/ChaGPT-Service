package com.izicap.test.Controller;


import org.springframework.web.bind.annotation.GetMapping;

import com.izicap.test.model.GptResponse;

import java.io.IOException;


public interface Controller {
    @GetMapping("/send{question}")
    public GptResponse send(String question) throws IOException;
}
