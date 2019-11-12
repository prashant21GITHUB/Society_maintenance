package com.maintenance.controllers;

import com.maintenance.model.ClientRequest;
import com.maintenance.model.ClientResponse;
import com.maintenance.model.DateWithPageSize;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.PagedMaintenanceRequestFilter;
import com.maintenance.model.RequestCategory;
import com.maintenance.model.RequestStatus;
import com.maintenance.model.RequestsResponse;
import com.maintenance.model.ServiceResponse;
import com.maintenance.model.UpdateStatusRequest;
import com.maintenance.services.IRequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller
public class RequestController implements IRequestController {

    @Autowired
    private IRequestService requestService;
    
    @RequestMapping(value = "/requests", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<RequestsResponse> getAllRequests(@RequestBody DateWithPageSize requestFilter) {
      
        return new ResponseEntity<RequestsResponse>(requestService.getAllRequests(requestFilter), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/requests/create", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<ClientResponse> createNewRequest(@RequestBody ClientRequest request) {
        ClientResponse response = requestService.createNewRequest(request);
        return new ResponseEntity<ClientResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/requests/update", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse> updateRequestStatus(@RequestBody UpdateStatusRequest statusRequest) {
        boolean result = requestService.updateStatus(statusRequest);
        String message = "Success";
        if (!result) {
            message = "RequestId doesn't exists";
        }
        return new ResponseEntity<ServiceResponse>(new ServiceResponse(result, message), HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/categories", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<RequestCategory>> getRequestCategories() {
        
        return new ResponseEntity<List<RequestCategory>>(requestService.getRequestCategories(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/requests/filters",consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<RequestsResponse> getRequestByFilters(@RequestBody PagedMaintenanceRequestFilter requestFilter) {

        return new ResponseEntity<RequestsResponse>(requestService.getRequestsByFilters(requestFilter), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/requests/page", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<RequestsResponse> getNextPageRequests(@RequestBody PagedMaintenanceRequestFilter requestFilter) {

        return new ResponseEntity<RequestsResponse>(requestService.getNextPageRequests(requestFilter), HttpStatus.OK);
    }
    
    public ResponseEntity<MaintenanceRequest> getRequestById(String reqId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResponseEntity<List<MaintenanceRequest>> getRequestsByCategory(RequestCategory reqCategory, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResponseEntity<List<MaintenanceRequest>> getRequestsByStatus(RequestStatus reqStatus, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResponseEntity<List<MaintenanceRequest>> getRequestsByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
