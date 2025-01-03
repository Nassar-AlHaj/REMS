package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.Property;
import com.rems.realestatemanagement.models.interfaces.PropertyDAO;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PropertyDAOImp implements PropertyDAO {

    private HibernateUtil hibernateUtil;
    private SessionFactory sessionFactory;

    public PropertyDAOImp() {
        hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public int insert(Property property) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(property);
            transaction.commit();
            return property.getId();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Property property) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Property property) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public List<String> getAllLocations() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select distinct location from Property", String.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            Property property = session.get(Property.class, id);
            if (property != null) {
                session.delete(property);
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
    public List<Property> getAllProperties() {

        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Property", Property.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Property findProperty(Property property) {

        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Property where propertyName = :name", Property.class)
                    .setParameter("name", property.getPropertyName())
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Property getProperty(int id) {

        Session session = sessionFactory.openSession();
        try {
            return session.get(Property.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void updateImage(int id, String imageUrl) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Property property = session.get(Property.class, id);
            if (property != null) {
                property.setImageProperty(imageUrl);
                session.update(property);
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
    public void deleteImage(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Property property = session.get(Property.class, id);
            if (property != null) {
                property.setImageProperty(null);
                session.update(property);
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
    public String getImage(int id) {
        Session session = sessionFactory.openSession();
        try {
            Property property = session.get(Property.class, id);
            return property != null ? property.getImageProperty() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}