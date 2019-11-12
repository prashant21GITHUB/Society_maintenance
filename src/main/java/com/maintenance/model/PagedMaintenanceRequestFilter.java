package com.maintenance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author bajpai
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagedMaintenanceRequestFilter extends MaintenanceRequestFilter {
    
    private int firstRow;
    private int resultSize;

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getResultSize() {
        return resultSize;
    }

    public void setResultSize(int resultSize) {
        this.resultSize = resultSize;
    }
}
