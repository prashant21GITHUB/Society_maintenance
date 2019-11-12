package com.maintenance.dao.translators;

import com.maintenance.dto.MaintenanceRequestDto;
import com.maintenance.model.MaintenanceRequest;
import com.maintenance.model.RequestCategory;
import com.maintenance.model.RequestStatus;
import com.maintenance.model.Tower;

/**
 *
 * @author bajpai
 */
public class MaintenanceRequestTranslator {
    
    public static MaintenanceRequestDto getMaintenanceRequestDto(MaintenanceRequest request) {
        MaintenanceRequestDto requestDto = new MaintenanceRequestDto();
        requestDto.setDate(request.getDate());
        requestDto.setCategory(request.getCategory().name());
        requestDto.setDetails(request.getRequestDetails());
        requestDto.setFlatNo(request.getFlatNo());
        requestDto.setTower(request.getTower().name());
        requestDto.setTime(request.getTime());
        requestDto.setStatus(request.getStatus().name());
        requestDto.setUserId(request.getUserId());
        return requestDto;
    }

    public static MaintenanceRequest getMaintenanceRequest(MaintenanceRequestDto dto) {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setDate(dto.getDate());
        request.setRequestDetails(dto.getDetails());
        request.setCategory(RequestCategory.valueOf(dto.getCategory()));
        request.setFlatNo(dto.getFlatNo());
        request.setStatus(RequestStatus.valueOf(dto.getStatus()));
        request.setTime(dto.getTime());
        request.setReqId(String.valueOf(dto.getReqNo()));
        request.setTower(Tower.valueOf(dto.getTower()));
        return request;
    }
}
