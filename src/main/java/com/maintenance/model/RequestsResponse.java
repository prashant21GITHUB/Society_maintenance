package com.maintenance.model;

import java.util.List;

/**
 *
 * @author bajpai
 */
public class RequestsResponse {
    
    private List<String> columns;
    private List<MaintenanceRequest> requests;
    private Long totalRequestsCount;
    private boolean hasNext;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<MaintenanceRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<MaintenanceRequest> requests) {
        this.requests = requests;
    }

    public Long getTotalRequestsCount() {
        return totalRequestsCount;
    }

    public void setTotalRequestsCount(Long totalRequestsCount) {
        this.totalRequestsCount = totalRequestsCount;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
