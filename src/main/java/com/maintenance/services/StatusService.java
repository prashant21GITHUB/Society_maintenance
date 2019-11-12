package com.maintenance.services;

import com.maintenance.model.RequestStatus;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author bajpai
 */
@Service
public class StatusService implements IStatusService {
    
    public List<RequestStatus> getAllStatuses() {
        return Arrays.asList(RequestStatus.values());
    }
    
}
