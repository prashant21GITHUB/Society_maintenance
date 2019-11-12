package com.maintenance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author bajpai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequest {
    
    private Integer flatNo;
    private Tower tower;
    private String mobileNo;
    private RequestCategory category;
    private String details;
    private String userId;

    public Integer getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public RequestCategory getCategory() {
        return category;
    }

    public void setCategory(RequestCategory category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
