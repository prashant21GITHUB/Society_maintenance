package com.maintenance.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author bajpai
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (null == sessionFactory) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        if (null == sessionFactory) {
            sessionFactory = getSessionFactory();
        }
        return sessionFactory.openSession();
    }

}
