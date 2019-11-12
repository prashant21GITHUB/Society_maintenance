package com.maintenance.dao;

import com.maintenance.model.MaintenanceRequest;
import java.util.List;

/**
 *
 * @author bajpai
 */
public class MaintenanceRequestList {
    private List<MaintenanceRequest> requests;
    private long totalRequestCount;
    private boolean hasNext;

    public List<MaintenanceRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<MaintenanceRequest> requests) {
        this.requests = requests;
    }

    public long getTotalRequestCount() {
        return totalRequestCount;
    }

    public void setTotalRequestCount(long totalRequestCount) {
        this.totalRequestCount = totalRequestCount;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    
    
}
