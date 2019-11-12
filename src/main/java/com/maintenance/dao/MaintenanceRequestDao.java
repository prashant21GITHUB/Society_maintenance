package com.maintenance.dao;

import com.maintenance.dao.translators.MaintenanceRequestTranslator;
import com.maintenance.dto.MaintenanceRequestDto;
import com.maintenance.dto.RequestIdGenerator;
import com.maintenance.hibernate.HibernateUtils;
import com.maintenance.model.DateWithPageSize;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.MaintenanceRequestFilter;
import com.maintenance.model.PagedMaintenanceRequestFilter;
import com.maintenance.model.UpdateStatusRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bajpai
 */
@Repository
public class MaintenanceRequestDao implements IMaintenanceRequestDao {

    private static final int ID_GENERATOR_KEY = 1;
    private static final int FIRST_RESULT = 0;
    private static final int MAX_RESULT_SIZE = 2;
    
    public DaoResponse createNewRequest(MaintenanceRequest request) {
        Session session = HibernateUtils.getSession();
        DaoResponse<MaintenanceRequest> daoResponse = new DaoResponse();
        try {
            session.beginTransaction();
            RequestIdGenerator idGenerator = session.get(RequestIdGenerator.class, ID_GENERATOR_KEY);
            MaintenanceRequestDto requestDto = MaintenanceRequestTranslator.getMaintenanceRequestDto(request);
            if(!requestDto.getDate().equals(idGenerator.getData())) {
                requestDto.setReqNo(1);
                idGenerator.setCounter(2);
                idGenerator.setData(requestDto.getDate());
            } else {      
                requestDto.setReqNo(idGenerator.getCounter());
                idGenerator.setCounter(idGenerator.getCounter() + 1);
            }
            requestDto.updateId();
            session.update(idGenerator);
            session.save(requestDto);
            session.getTransaction().commit();
            daoResponse.setSuccess(Boolean.TRUE);
            daoResponse.setReqId(requestDto.getId());
        } catch(Exception e) {
            daoResponse.setSuccess(Boolean.FALSE);
            daoResponse.setMessage(e.getMessage());
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return daoResponse;
    }

    public DaoResponse<MaintenanceRequestList> getAllRequests(DateWithPageSize dateWithPageSize) {
        PagedMaintenanceRequestFilter filter = new PagedMaintenanceRequestFilter();
        filter.setDateCreation(dateWithPageSize.getDateCreation());
        filter.setResultSize(dateWithPageSize.getResultSize());
        return getRequestsByFilter(filter);
    }
    
    public Integer getMaintenanceRequestCount(String date) {
        Session session = HibernateUtils.getSession();
        Integer rowCount = 0;
        try {
            Criteria criteria = session.createCriteria(MaintenanceRequestDto.class)
                    .setProjection(Projections.rowCount());
            List result = criteria.list();
            if (!result.isEmpty()) {
                rowCount = (Integer) result.get(0);
            }
        } catch (Exception e) {
            return -1;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rowCount;
    }
    
    public DaoResponse<MaintenanceRequestList> getNextPageRequests(PagedMaintenanceRequestFilter filter) {
        DaoResponse<MaintenanceRequestList> daoResponse = new DaoResponse<MaintenanceRequestList>();
        Session session = HibernateUtils.getSession();
        try {

//            session.beginTransaction();
            Criteria criteria = session.createCriteria(MaintenanceRequestDto.class);
            applyFiltersOnCriteria(filter, criteria);
            long rowCount = getRowCount(criteria);
            criteria.setFirstResult(filter.getFirstRow());
            criteria.setMaxResults(filter.getResultSize());
            criteria.addOrder(Order.desc("date"));
            criteria.addOrder(Order.desc("reqNo"));
            List<MaintenanceRequestDto> requestDtoList = criteria.list();
            List<MaintenanceRequest> requests = new ArrayList<MaintenanceRequest>();
            for (MaintenanceRequestDto dto : requestDtoList) {
                requests.add(MaintenanceRequestTranslator.getMaintenanceRequest(dto));
            }
//            Collections.sort(requests);
            MaintenanceRequestList requestList = new MaintenanceRequestList();
            requestList.setRequests(requests);
            requestList.setHasNext(filter.getResultSize() == requests.size());
            requestList.setTotalRequestCount(rowCount);
            daoResponse.setRequest(requestList);
            daoResponse.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            daoResponse.setSuccess(Boolean.FALSE);
            daoResponse.setMessage(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return daoResponse;
    }


    public DaoResponse<MaintenanceRequestList> getRequestsByFilter(PagedMaintenanceRequestFilter filter) {
        DaoResponse<MaintenanceRequestList> daoResponse = new DaoResponse<MaintenanceRequestList>();
        Session session = HibernateUtils.getSession();
        try {
            
//            session.beginTransaction();
            Criteria criteria = session.createCriteria(MaintenanceRequestDto.class);
            applyFiltersOnCriteria(filter, criteria);
            long rowCount = getRowCount(criteria);
            criteria.setFirstResult(filter.getFirstRow());
            criteria.setMaxResults(filter.getResultSize());
            criteria.addOrder(Order.desc("date"));
            criteria.addOrder(Order.desc("reqNo"));
            List<MaintenanceRequestDto> requestDtoList = criteria.list();
            List<MaintenanceRequest> requests = new ArrayList<MaintenanceRequest>();
            for (MaintenanceRequestDto dto : requestDtoList) {
                requests.add(MaintenanceRequestTranslator.getMaintenanceRequest(dto));
            }
//            Collections.sort(requests);
            MaintenanceRequestList requestsList = new MaintenanceRequestList();
            requestsList.setRequests(requests);
            requestsList.setTotalRequestCount(rowCount);
            requestsList.setHasNext(rowCount > MAX_RESULT_SIZE);
            daoResponse.setRequest(requestsList);
            daoResponse.setSuccess(Boolean.TRUE);
        } catch(Exception e) {
            daoResponse.setSuccess(Boolean.FALSE);
            daoResponse.setMessage(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return daoResponse;
    }

    private void applyFiltersOnCriteria(MaintenanceRequestFilter filter, Criteria criteria) {
        if(filter.getDateCreation() != null) {
            criteria.add(Restrictions.eq("date", filter.getDateCreation()));
        }
        
        if (filter.getStatuses()!= null) {
            criteria.add(Restrictions.in("status", filter.getStatuses()));
        } else if(filter.getStatus() != null) {
            criteria.add(Restrictions.eq("status", filter.getStatus().name()));
        }
        
        if (filter.getTowers()!= null) {
            criteria.add(Restrictions.in("tower", filter.getTowers()));
        } else if(filter.getTower() != null) {
            criteria.add(Restrictions.eq("tower", filter.getTower().name()));
        }
        
        if (filter.getFlatNo() != null) {
            criteria.add(Restrictions.eq("flatNo", filter.getFlatNo()));
        }
        if(filter.getStartDate() != null && filter.getEndDate() != null) {
            criteria.add(Restrictions.ge("date", filter.getStartDate())).add(Restrictions.le("date", filter.getEndDate()));
        }
        
        if(filter.getCategories() != null) {
            criteria.add(Restrictions.in("category", filter.getCategories()));
        } else if (filter.getCategory() != null) {
            criteria.add(Restrictions.eq("category", filter.getCategory().name()));
        }
    }

    public DaoResponse updateRequestStatus(UpdateStatusRequest updateRequest) {
        String date = updateRequest.getDate();
        String reqNo = updateRequest.getReqNo();
        String reqId = IdGeneratorUtils.generateMaintenanceRequestId(date, reqNo);
        Session session = HibernateUtils.getSession();
        DaoResponse daoResponse = new DaoResponse();
        try {
            
            session.beginTransaction();
            MaintenanceRequestDto requestDto = session.get(MaintenanceRequestDto.class, reqId);
            requestDto.setStatus(updateRequest.getStatus().name());
            session.update(requestDto);
            session.getTransaction().commit();
            daoResponse.setSuccess(Boolean.TRUE);
        } catch(Exception e) {
            daoResponse.setSuccess(Boolean.FALSE);
            daoResponse.setMessage(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return daoResponse;
    }

    private long getRowCount(Criteria criteria) {
        long rowCount = 0;
        criteria.setProjection(Projections.rowCount());
        List result = criteria.list();
        if (!result.isEmpty()) {
            rowCount = (Long) result.get(0);
        }
        criteria.setProjection(null);
        return rowCount;
    }
    
}
