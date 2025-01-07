package com.rems.realestatemanagement.models;

import com.rems.realestatemanagement.models.interfaces.OffersDAO;
import com.rems.realestatemanagement.models.services.Offers;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OffersDAOimp implements OffersDAO {

    private SessionFactory sessionFactory;
    public OffersDAOimp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    @Override
    public void CreateOffer(Offers offer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(offer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Offers> getAllOffers() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Offers", Offers.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return List.of();
    }


}
