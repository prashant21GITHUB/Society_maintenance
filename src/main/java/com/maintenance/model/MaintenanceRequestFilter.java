package com.maintenance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author bajpai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaintenanceRequestFilter {
    
    private RequestStatus status;
    private RequestCategory category;
    private Tower tower;
    private Integer flatNo;
    private String dateCreation;
    private String startDate;
    private String endDate;
    private List<String> statuses;
    private List<String> categories;
    private List<String> towers;

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestCategory getCategory() {
        return category;
    }

    public void setCategory(RequestCategory category) {
        this.category = category;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Integer getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTowers() {
        return towers;
    }

    public void setTowers(List<String> towers) {
        this.towers = towers;
    }
    
    
}
