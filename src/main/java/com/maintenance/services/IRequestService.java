package com.maintenance.services;

import com.maintenance.model.ClientRequest;
import com.maintenance.model.ClientResponse;
import com.maintenance.model.DateWithPageSize;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.PagedMaintenanceRequestFilter;
import com.maintenance.model.RequestCategory;
import com.maintenance.model.RequestStatus;
import com.maintenance.model.RequestsResponse;
import com.maintenance.model.UpdateStatusRequest;
import java.util.List;

/**
 *
 * @author bajpai
 */
public interface IRequestService {
    
    ClientResponse createNewRequest(ClientRequest request);

    RequestsResponse getAllRequests(DateWithPageSize dateWithPageSize);

    ClientResponse updateRequest(String reqId);

    MaintenanceRequest getRequestById(String reqId);

    List<MaintenanceRequest> getRequestsByCategory(RequestCategory reqCategory, String date);

    List<MaintenanceRequest> getRequestsByStatus(RequestStatus reqStatus, String date);

    List<MaintenanceRequest> getRequestsByDate(String date);
    
    boolean updateStatus(UpdateStatusRequest statusRequest);

    public List<RequestCategory> getRequestCategories();

    public RequestsResponse getRequestsByFilters(PagedMaintenanceRequestFilter requestFilter);

    public RequestsResponse getNextPageRequests(PagedMaintenanceRequestFilter requestFilter);
}
