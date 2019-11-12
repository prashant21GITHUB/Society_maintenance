package com.maintenance.dao;

/**
 *
 * @author bajpai
 */
public class IdGeneratorUtils {

    private static final String _UNDERSCORE = "_";
    
    public static String generateMaintenanceRequestId(String date, String reqNo) {
        return date + _UNDERSCORE + reqNo;
    }
    
}
