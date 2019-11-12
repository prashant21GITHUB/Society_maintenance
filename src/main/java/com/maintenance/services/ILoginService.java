package com.maintenance.services;

/**
 *
 * @author bajpai
 */
public interface ILoginService {
    
    boolean login(String username, String password);
    
    boolean logout(String username);
}
