package com.maintenance.services;

import com.maintenance.dao.DaoResponse;
import com.maintenance.dao.IMaintenanceRequestDao;
import com.maintenance.dao.MaintenanceRequestList;
import com.maintenance.model.ClientRequest;
import com.maintenance.model.ClientResponse;
import com.maintenance.model.DateWithPageSize;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.MaintenanceRequestFilter;
import com.maintenance.model.PagedMaintenanceRequestFilter;
import com.maintenance.model.RequestCategory;
import com.maintenance.model.RequestStatus;
import com.maintenance.model.RequestsResponse;
import com.maintenance.model.UpdateStatusRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bajpai
 */
@Service
public class RequestService implements IRequestService {

    private final List<String> columns = Arrays.asList("Request No", "Date", "Time","Flat No", "Category", "Status", "Details");
    private final SimpleDateFormat dateFormat;
    private final SimpleDateFormat timeFormat;
    
    @Autowired
    private IMaintenanceRequestDao maintenanceRequestDao;

    public RequestService() {
        dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        timeFormat = new SimpleDateFormat("hh:mm a");
        timeFormat.setTimeZone(TimeZone.getTimeZone("IST"));
    }
    
    public ClientResponse createNewRequest(ClientRequest req) {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setCategory(req.getCategory());
        request.setFlatNo(req.getFlatNo());
        Date date = new Date();
        request.setDate(dateFormat.format(date));
        request.setTime(timeFormat.format(date));
        request.setRequestDetails(req.getDetails());
        request.setStatus(RequestStatus.New);
        request.setMobileNo(req.getMobileNo());
        request.setTower(req.getTower());
//        String id = String.valueOf(++count);
//        request.setReqId(id);
//        requests.add(request);
//        requestmap.put(id, request);
        DaoResponse daoResponse = maintenanceRequestDao.createNewRequest(request);
        ClientResponse response = new ClientResponse();
        response.setReqId(daoResponse.getReqId());
        response.setMessage(daoResponse.getMessage());
        response.setSuccess(daoResponse.getSuccess());
        return response;
    }

    public RequestsResponse getAllRequests(DateWithPageSize dateWithPageSize) {
        DaoResponse<MaintenanceRequestList> daoResponse = this.maintenanceRequestDao.getAllRequests(dateWithPageSize);
        RequestsResponse response = new RequestsResponse();
        MaintenanceRequestList serviceResponse = daoResponse.getRequest();
        response.setRequests(serviceResponse.getRequests());
        response.setColumns(columns);
        response.setTotalRequestsCount(serviceResponse.getTotalRequestCount());
        response.setHasNext(serviceResponse.isHasNext());
        return response;
    }

    public ClientResponse updateRequest(String reqId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MaintenanceRequest getRequestById(String reqId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MaintenanceRequest> getRequestsByCategory(RequestCategory reqCategory, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MaintenanceRequest> getRequestsByStatus(RequestStatus reqStatus, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MaintenanceRequest> getRequestsByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateStatus(UpdateStatusRequest statusRequest) {
        DaoResponse daoResponse = this.maintenanceRequestDao.updateRequestStatus(statusRequest);
        if(daoResponse.getSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public List<RequestCategory> getRequestCategories() {
        return Arrays.asList(RequestCategory.values());
    }

    public RequestsResponse getRequestsByFilters(PagedMaintenanceRequestFilter requestFilter) {
        RequestsResponse response = new RequestsResponse();
        DaoResponse<MaintenanceRequestList> daoResponse = this.maintenanceRequestDao.getRequestsByFilter(requestFilter);
        MaintenanceRequestList serviceResponse = daoResponse.getRequest();
        response.setRequests(serviceResponse.getRequests());
        response.setColumns(columns);
        response.setTotalRequestsCount(serviceResponse.getTotalRequestCount());
        response.setHasNext(serviceResponse.isHasNext());
        return response;
    }

    private boolean validRequest(MaintenanceRequest req, MaintenanceRequestFilter requestFilter) {
        boolean result = true;
        if(requestFilter.getCategory() != null) {
            result = result && req.getCategory().equals(requestFilter.getCategory());
        }
        if(requestFilter.getStatus() != null) {
            result = result && req.getStatus().equals(requestFilter.getStatus());
        }
        if(requestFilter.getTower() != null) {
            result = result && req.getTower().equals(requestFilter.getTower());
        }
        if(requestFilter.getFlatNo() != null) {
            result = result && req.getFlatNo().equals(requestFilter.getFlatNo());
        }
        if(requestFilter.getDateCreation() != null) {
            result = result && req.getDate().equals(requestFilter.getDateCreation());
        }
        return result;
    }

    public RequestsResponse getNextPageRequests(PagedMaintenanceRequestFilter requestFilter) {
        RequestsResponse response = new RequestsResponse();
        DaoResponse<MaintenanceRequestList> daoResponse = this.maintenanceRequestDao.getNextPageRequests(requestFilter);
        MaintenanceRequestList serviceResponse = daoResponse.getRequest();
        response.setRequests(serviceResponse.getRequests());
        response.setColumns(columns);
        response.setTotalRequestsCount(serviceResponse.getTotalRequestCount());
        response.setHasNext(serviceResponse.isHasNext());
        return response;
    }


}
