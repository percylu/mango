package com.weehai.mango.core.http;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 4564124491192825748L;

	private int code;
	private String message;
	public CustomException() {
        super();
    }
 
    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
        this.setMessage(message);
    }


}
