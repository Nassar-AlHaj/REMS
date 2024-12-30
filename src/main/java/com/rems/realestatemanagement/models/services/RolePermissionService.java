package com.rems.realestatemanagement.models.services;

import com.rems.realestatemanagement.models.Permissions;
import com.rems.realestatemanagement.models.Role;
import com.rems.realestatemanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class RolePermissionService {

    private HibernateUtil hibernateUtil;
    private SessionFactory sessionFactory;

    public RolePermissionService() {
        hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    public void setUpRolesAndPermissions() {

        Permissions addAgent = findPermissionByName("ADD_AGENT");
        Permissions addClient = findPermissionByName("ADD_CLIENT");
        Permissions addInteraction = findPermissionByName("ADD_INTERACTION");
        Permissions viewDashboard = findPermissionByName("VIEW_DASHBORED");
        Permissions deleteAgent = findPermissionByName("DELETE_AGENT");
        Permissions addProperty = findPermissionByName("ADD_PROPERTY");
        Permissions updateProperty = findPermissionByName("UPDATE_PROPERTY");
        Permissions deleteProperty = findPermissionByName("DELETE_PROPERTY");
        Permissions filterProperty = findPermissionByName("FILTER_PROPERTY");
        Permissions updateClient = findPermissionByName("UPDATE_CLIENT");
        Permissions deleteClient = findPermissionByName("DELETE_CLIENT");
        Permissions createOffer = findPermissionByName("CREATE_OFFER");
        Permissions editOffer = findPermissionByName("EDIT_OFFER");
        Permissions deleteOffer = findPermissionByName("Delete_OFFER");
        Permissions viewInteractions = findPermissionByName("VIEW_INTERACTIONS");


        Role adminRole = findRoleByName("Admin");
        Role agentRole = findRoleByName("Agent");


        Set<Permissions> adminPermissions = new HashSet<>();
        adminPermissions.add(addAgent);
        adminPermissions.add(deleteAgent);
        adminPermissions.add(viewDashboard);
        adminPermissions.add(addProperty);
        adminPermissions.add(updateProperty);
        adminPermissions.add(deleteProperty);
        adminPermissions.add(filterProperty);
        adminPermissions.add(viewInteractions);
        adminPermissions.add(createOffer);
        adminPermissions.add(editOffer);
        adminPermissions.add(deleteOffer);

        adminRole.setPermissions(adminPermissions);
        save(adminRole);


        Set<Permissions> agentPermissions = new HashSet<>();
        agentPermissions.add(addClient);
        agentPermissions.add(updateClient);
        agentPermissions.add(deleteClient);
        agentPermissions.add(addInteraction);
        agentPermissions.add(addProperty);
        agentPermissions.add(updateProperty);
        agentPermissions.add(filterProperty);
        agentPermissions.add(createOffer);
        agentPermissions.add(editOffer);

        agentRole.setPermissions(agentPermissions);
        save(agentRole);
    }

    private Permissions findPermissionByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Permissions permission = session.createQuery("FROM Permissions WHERE name = :name", Permissions.class)
                .setParameter("name", name)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return permission;
    }

    private Role findRoleByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Role role = session.createQuery("FROM Role WHERE name = :name", Role.class)
                .setParameter("name", name)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return role;
    }

    private void save(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(role);
        transaction.commit();
        session.close();
    }
}
