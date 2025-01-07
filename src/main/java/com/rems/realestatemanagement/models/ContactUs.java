package com.rems.realestatemanagement.models;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class ContactUs {

    @Id
    @GeneratedValue
    private int idContact;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "descriptions")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return idContact;
    }

    public void setId(int id) {
        this.idContact = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ContactUs() {
    }

    public ContactUs(String description, String country, String email, String lastName, String firstName, int id) {
        this.description = description;
        this.country = country;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.idContact = id;
    }

}