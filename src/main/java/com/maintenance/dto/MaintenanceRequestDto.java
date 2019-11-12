package com.maintenance.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bajpai
 */
@Entity
@Table(name = "MaintenanceRequest")
public class MaintenanceRequestDto {
    
    @Id
    private String id;  // id = date_reqNo
    private String date;
    private Integer reqNo;
    private Integer flatNo;
    private String tower;
    private String userId;
    private String time;
    private String category;
    private String status;
    private String details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getReqNo() {
        return reqNo;
    }

    public void setReqNo(Integer reqNo) {
        this.reqNo = reqNo;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public void updateId() {
        this.id = this.date + "_" + this.reqNo;
    }
}
