package com.chatapp.response;

public abstract class RestResponse {
	
	private String status;
	private String message;
	private Integer responseCode;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public RestResponse(String status, String message, Integer responseCode) {
		super();
		this.status = status;
		this.message = message;
		this.responseCode = responseCode;
	}
	public RestResponse() {
		super();
	}
	
}
