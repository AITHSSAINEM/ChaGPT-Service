package com.izicap.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.izicap.test.Properties.PropertiesGpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Request implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String model;
    private String prompt;
    private Double temperature;
    @JsonProperty("max_tokens")
    private Integer max_Tokens;
    public static Request requestUserTogGpt(String message){
        Request request=new Request();
        request.setModel(PropertiesGpt.MODEL);
        request.setPrompt(message);
        request.setTemperature(PropertiesGpt.TEMPERATURE);
        request.setMax_Tokens(PropertiesGpt.MAX_TOKEN);
        return request;
    }
	
    
}
