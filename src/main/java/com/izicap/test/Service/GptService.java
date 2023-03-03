package com.izicap.test.Service;

import java.io.IOException;

import com.izicap.test.exceptions.EmptyQuestionExcetion;
import com.izicap.test.model.GptResponse;

public interface GptService {
    public GptResponse sendToGpt(String message) throws IOException, EmptyQuestionExcetion;
}
