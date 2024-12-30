package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.ContactUs;

import java.util.List;

public interface ContactUsDAO {

    int insert(ContactUs contact);
    void update(ContactUs contact);
    void delete(ContactUs contact);
    void delete(int id);
    List<ContactUs> getAllContacts();
    ContactUs findContact(ContactUs contact);
    ContactUs getContact(int id);
}