package com.desafiovotacao.dto;

public class ApiResponse<T> {
    private T data;
    private String error;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
    }
}
