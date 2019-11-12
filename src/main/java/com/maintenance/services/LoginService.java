package com.maintenance.services;

import org.springframework.stereotype.Service;

/**
 *
 * @author bajpai
 */
@Service
public class LoginService implements ILoginService {

    public boolean login(String username, String password) {
        return "admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password);
    }

    public boolean logout(String username) {
        return "admin".equalsIgnoreCase(username);
    }
    
}
