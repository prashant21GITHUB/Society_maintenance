
package com.maintenance.controllers;

import com.maintenance.model.ClientRequest;
import com.maintenance.model.ClientResponse;
import com.maintenance.model.DateWithPageSize;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.RequestCategory;
import com.maintenance.model.RequestStatus;
import com.maintenance.model.RequestsResponse;
import com.maintenance.model.ServiceResponse;
import com.maintenance.model.UpdateStatusRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author bajpai
 */
interface IRequestController {
    
    ResponseEntity<ClientResponse> createNewRequest(ClientRequest request);
    
    ResponseEntity<RequestsResponse> getAllRequests(DateWithPageSize requestFilter);
    
    ResponseEntity<ServiceResponse> updateRequestStatus(UpdateStatusRequest statusReq);
    
    ResponseEntity<MaintenanceRequest> getRequestById(String reqId);
    
    ResponseEntity<List<MaintenanceRequest>> getRequestsByCategory(RequestCategory reqCategory, String date);
    
    ResponseEntity<List<MaintenanceRequest>> getRequestsByStatus(RequestStatus reqStatus, String date);
    
    ResponseEntity<List<MaintenanceRequest>> getRequestsByDate(String date);
    
    ResponseEntity<List<RequestCategory>> getRequestCategories();
}
