package com.codelabs_coding.petrescue.utils.networkUtils;

public class ResponseWrapper<T> {
    private boolean status;
    private String message;
    private T data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
