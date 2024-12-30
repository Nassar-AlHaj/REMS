
package com.rems.realestatemanagement.util;

import com.rems.realestatemanagement.models.Property;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            Configuration configuration = new Configuration();


            configuration.configure("hibernate.cfg.xml");


            configuration.addAnnotatedClass(Property.class);


            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static HibernateUtil getInstance(){
        HibernateUtil instance = new HibernateUtil();
        if(instance == null){
            instance  = new HibernateUtil();
        }
        return instance;
    }
}