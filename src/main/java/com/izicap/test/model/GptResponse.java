package com.izicap.test.model;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GptResponse implements Serializable {

    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;

}
