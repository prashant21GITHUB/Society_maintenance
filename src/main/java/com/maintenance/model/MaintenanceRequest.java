package com.maintenance.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class MaintenanceRequest implements Comparable<MaintenanceRequest>{
	
	private String reqId;
	private RequestCategory category;
	private String requestDetails;
	private String date;
	private String time;
	private RequestStatus status;
        private String mobileNo;
        private Tower tower;
        private Integer flatNo;
        private String userId;
	
	public MaintenanceRequest() {
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
		LocalTime localTime = LocalTime.now();
		date = localDate.toString();
		time = localTime.toString();
	}
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public RequestCategory getCategory() {
		return category;
	}
	public void setCategory(RequestCategory category) {
		this.category = category;
	}
	public String getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.reqId != null ? this.reqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaintenanceRequest other = (MaintenanceRequest) obj;
        if ((this.reqId == null) ? (other.reqId != null) : !this.reqId.equals(other.reqId)) {
            return false;
        }
        return true;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Integer getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }

    public int compareTo(MaintenanceRequest t) {
        int first = Integer.parseInt(reqId);
        int second = Integer.parseInt(t.getReqId());
        if(first == second) return 0;
        if(first > second) return -1;
        else return 1;
    }

}
