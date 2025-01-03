package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.interfaces.UsersDOA;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UsersDOAImp implements UsersDOA {

    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;
    public UsersDOAImp() {
        hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public void insert(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }



    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }


    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<User> getAllAgents() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from User where role.name = 'Agent'", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }


    @Override
    public void delete(User users) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(users);
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
            User users = session.get(User.class, id);
            if (users != null) {
                session.delete(users);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


}