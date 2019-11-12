package com.maintenance.services;

import com.maintenance.model.RequestStatus;
import java.util.List;

/**
 *
 * @author bajpai
 */
public interface IStatusService {

    List<RequestStatus> getAllStatuses();
}
