package com.maintenance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author bajpai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateStatusRequest {
    private String reqNo;
    private String date;
    private RequestStatus status;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
