package com.rems.realestatemanagement.util;


import com.rems.realestatemanagement.models.Permissions;
import com.rems.realestatemanagement.models.Role;
import com.rems.realestatemanagement.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Permissions.class);


            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static HibernateUtil getInstance(){
        HibernateUtil instance = new HibernateUtil();
        if(instance == null){
            instance  = new HibernateUtil();
        }
        return instance;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}


