package com.maintenance.model;

/**
 *
 * @author bajpai
 */
public class ServiceResponse {

    private final boolean success;
    private final String message;

    public ServiceResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
