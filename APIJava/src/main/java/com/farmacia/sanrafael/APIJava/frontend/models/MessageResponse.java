package com.farmacia.sanrafael.APIJava.frontend.models;

public class MessageResponse<T> {
    private String message;
    private T data;
    private String timestamp;

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
