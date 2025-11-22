package com.rohitPal.localBusinessDirectory.exception;

public class ApiError {
    private String message;
    private String timeStamp ;
    private String error;
    private String path;
    private int statusCode;

    public ApiError() {
    }

    public ApiError(String message, String timeStamp, String error, String path, int statusCode) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.error = error;
        this.path = path;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
