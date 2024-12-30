package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.Role;
import com.rems.realestatemanagement.models.interfaces.RoleDOA;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class RoleDOAImp implements RoleDOA {
    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;


    public RoleDOAImp() {
        hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Role role) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Role findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Role role = session.get(Role.class, id);
        session.getTransaction().commit();
        session.close();
        return role;
    }

    public Role findByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Role role = session.createQuery("FROM Role WHERE name = :name", Role.class)
                .setParameter("name", name)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return role;
    }

}
