package com.maintenance.dao;

import com.maintenance.model.DateWithPageSize;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.PagedMaintenanceRequestFilter;
import com.maintenance.model.UpdateStatusRequest;

/**
 *
 * @author bajpai
 */
public interface IMaintenanceRequestDao {
    
    DaoResponse<String> createNewRequest(MaintenanceRequest request);
    
    DaoResponse<MaintenanceRequestList> getAllRequests(DateWithPageSize dateWithPageSize);
    
    DaoResponse<MaintenanceRequestList> getRequestsByFilter(PagedMaintenanceRequestFilter filter);
    
    DaoResponse updateRequestStatus(UpdateStatusRequest updateRequest);
    
    DaoResponse<MaintenanceRequestList> getNextPageRequests(PagedMaintenanceRequestFilter filter);
}
