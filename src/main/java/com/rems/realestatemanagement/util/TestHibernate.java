package com.rems.realestatemanagement.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();


        System.out.println("Hibernate connected to the database!");

        session.close();
        factory.close();
    }
}
