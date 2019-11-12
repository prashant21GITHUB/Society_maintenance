package com.maintenance.dao;

/**
 *
 * @author bajpai
 */
public class DaoResponse<T> {
    
    private Boolean success = Boolean.FALSE;
    private String message;
    private String reqId;
    private T request;
    
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
