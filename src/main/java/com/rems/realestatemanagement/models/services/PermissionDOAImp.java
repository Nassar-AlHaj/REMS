package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.Permissions;
import com.rems.realestatemanagement.models.interfaces.PermissionDOA;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PermissionDOAImp implements PermissionDOA {
    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;

    public PermissionDOAImp() {
        hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = hibernateUtil.getSessionFactory();
    }
    @Override
    public void save(Permissions permission) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(permission);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Permissions findById(int id) {
        Session session = sessionFactory.openSession();
        Permissions permission = session.get(Permissions.class, id);
        session.close();
        return permission;
    }

    @Override
    public Permissions findByName(String name) {
        Session session = sessionFactory.openSession();
        Query<Permissions> query = session.createQuery("FROM Permissions WHERE name = :permissionName", Permissions.class);
        query.setParameter("permissionName", name);
        Permissions permission = query.uniqueResult();
        session.close();
        return permission;
    }







}
