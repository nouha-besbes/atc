package com.authentication.controller.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

    private Date timestamp;
    private HttpStatus status;
    private List<String> errors;
    private String message;

    public ApiError(Date timestamp, HttpStatus status, List<String> errors, String message) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
