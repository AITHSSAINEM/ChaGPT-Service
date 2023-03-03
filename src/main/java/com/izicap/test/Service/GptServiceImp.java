package com.izicap.test.Service;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.izicap.test.Properties.PropertiesGpt;
import com.izicap.test.exceptions.EmptyQuestionExcetion;
import com.izicap.test.model.GptResponse;
import com.izicap.test.model.Request;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class GptServiceImp implements GptService{

    public GptResponse sendToGpt(String message) throws IOException, EmptyQuestionExcetion {
    	log.info("message :"+message);
    	if(message.isBlank()) {
        	throw new EmptyQuestionExcetion("Empty question !");
    	}
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", PropertiesGpt.Content_Type);
        headers.add("Authorization", PropertiesGpt.BEARER + PropertiesGpt.API_KEY);
        Request request = Request.requestUserTogGpt(message);
        HttpEntity<Request> httpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<GptResponse> responseEntity = restTemplate.postForEntity(
                PropertiesGpt.CURL,
                httpEntity,
                GptResponse.class
        );
        //Add to csv
        File csvFile = new File("src/main/resources/chat.csv");
        //StringBuilder stringBuilder = new StringBuilder();
        FileWriter fileWriter = new FileWriter(csvFile,true);
        int nbLines= Files.readAllLines(Path.of("src/main/resources/chat.csv")).size();
        System.out.println(nbLines);
        if(nbLines>0){
            fileWriter.write(message+";"+responseEntity.getBody().getChoices().get(0).getText().replaceAll("[-+.^:,`,\n,`\t,;]"," ")+"\n");
        }else {
            fileWriter.write("Question;Response \n");
            fileWriter.write(message+";"+responseEntity.getBody().getChoices().get(0).getText().replaceAll("[-+.^:,`,\n,`\t,;]"," ")+"\n");
        }
        fileWriter.close();
        return responseEntity.getBody();

    }

    }
