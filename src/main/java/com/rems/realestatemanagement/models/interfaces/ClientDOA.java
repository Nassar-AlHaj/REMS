package com.rems.realestatemanagement.models.interfaces;



import com.rems.realestatemanagement.models.Client;

import java.util.List;


public interface ClientDOA {
    void save(Client client);
    void update(Client client);
    void delete(Client client);
    List<Client> findAll();
    Client findById(int id);
}