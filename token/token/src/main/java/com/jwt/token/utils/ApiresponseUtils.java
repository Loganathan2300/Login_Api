package com.jwt.token.utils;

import org.springframework.http.HttpStatus;


public class ApiresponseUtils {

	private Integer status;
	private Object data;
	private Object data1;
	private Object error;

	public ApiresponseUtils() {
		this.status = HttpStatus.OK.value();
		this.data = data;
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData1() {
		return data1;
	}

	public void setData1(Object data1) {
		this.data1 = data1;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}
	
	
}
