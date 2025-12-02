package com.t2.cocobolo.chat.dto;

public class ChatResponse {
    private String response;
    private boolean success;
    private String error;

    public ChatResponse() {
    }

    public ChatResponse(String response) {
        this.response = response;
        this.success = true;
    }

    public static ChatResponse error(String error) {
        ChatResponse r = new ChatResponse();
        r.success = false;
        r.error = error;
        return r;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
