package com.rems.realestatemanagement.util;

import com.rems.realestatemanagement.models.services.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

// Usage example
public class Main {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        // Interact with the database
        Client client = new Client();
        client.setFname("John Doe");
        client.setEmail("johndoe@example.com");
        session.save(client);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
