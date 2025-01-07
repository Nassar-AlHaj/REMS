package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.ContactUs;
import com.rems.realestatemanagement.models.interfaces.ContactUsDAO;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ContactUsDAOImp implements ContactUsDAO {

    private HibernateUtil hibernateUtil;
    private SessionFactory sessionFactory;

    public ContactUsDAOImp() {
        hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public int insert(ContactUs contact) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(contact);
            transaction.commit();
            return contact.getId();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(ContactUs contact) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(contact);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(ContactUs contact) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(contact);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ContactUs contact = session.get(ContactUs.class, id);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<ContactUs> getAllContacts() {

        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from ContactUs", ContactUs.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ContactUs findContact(ContactUs contact) {

        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from ContactUs where email = :email", ContactUs.class)
                    .setParameter("email", contact.getEmail())
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ContactUs getContact(int id) {

        Session session = sessionFactory.openSession();
        try {
            return session.get(ContactUs.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}