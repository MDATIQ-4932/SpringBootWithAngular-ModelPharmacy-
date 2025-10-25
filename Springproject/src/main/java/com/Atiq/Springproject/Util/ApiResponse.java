package com.Atiq.Springproject.Util;




import java.util.HashMap;
import java.util.Map;




public class ApiResponse {

    private boolean successful;
    private String message;
    private Map<String, Object> data = new HashMap<>(); // Holds the response data

//    // Constructor
//    public ApiResponse() {
//        this.successful = successful;
//        this.message = message;
//
//    }
//
//    // Getters and Setters
//    public boolean isSuccessful() {
//        return successful;
//    }
//
//    public void setSuccessful(boolean successful) {
//        this.successful = successful;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public void setData(Map<String, Object> data) {
//        this.data = data;
//    }
//


    public ApiResponse() {
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
