package com.izicap.test.model;

import lombok.Data;


import java.io.Serializable;


@Data
public class Choice implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
    private Integer index;
}
