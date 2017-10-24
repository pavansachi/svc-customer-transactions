package org.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomerResponse {

	private Object data;
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}

	private Long timestamp;
	private List<ErrorResponse> errors = null;
	
	public CustomerResponse() {
		this.timestamp = Calendar.getInstance().getTimeInMillis();
		errors = new ArrayList<ErrorResponse>();
	}
	
	public void addError(String code, String message) {
		errors.add(new ErrorResponse(code, message));
	}

	public CustomerResponse(Object data) {
		this();
		this.data = data;
	}

	@Override
	public String toString() {
		return "CustomerResponse [data=" + data + ", timestamp=" + timestamp + ", errors=" + errors + "]";
	}
	
}
