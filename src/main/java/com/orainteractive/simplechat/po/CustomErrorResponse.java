package com.orainteractive.simplechat.po;

import java.util.HashMap;

public class CustomErrorResponse {
	private String message;
	private HashMap<String, String> errors = new HashMap<String, String>();
	private String meta;	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}
}
