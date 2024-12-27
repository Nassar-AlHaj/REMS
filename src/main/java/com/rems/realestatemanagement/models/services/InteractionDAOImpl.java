package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.Interaction;
import com.rems.realestatemanagement.models.interfaces.InteractionDAO;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class InteractionDAOImpl implements InteractionDAO {

    @Override
    public void createInteraction(Interaction interaction) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(interaction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error creating interaction: " + e.getMessage(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Interaction> getAllInteractions() {
        List<Interaction> interactions = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            interactions = session.createQuery("FROM Interaction", Interaction.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving interactions: " + e.getMessage(), e);
        }
        return interactions;
    }
    @Override
    public void deleteInteraction(Interaction interaction) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(interaction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting interaction: " + e.getMessage(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}