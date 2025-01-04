package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.interfaces.UsersDAO;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

public class UsersDAOImp implements UsersDAO {

    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;
    public UsersDAOImp() {
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
        } else {
            return null;
        }
    }

    @Override
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


}
