package com.emb.techborg.exception;

import org.springframework.stereotype.Component;

@Component
public class ResourceAlreadyExistsException {
	
	private String errorCode;
	private String errorMessage;
	
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException() {
		
	}

	public ResourceAlreadyExistsException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
    
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
