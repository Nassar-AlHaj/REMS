package com.rems.realestatemanagement.models.services;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column(name = "Client Name",nullable = false)
    public String ClientNameField;

    @Column(name = "Property ID", nullable = false)
    public String PropertyId;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "Amount",nullable = false)
    private double Amount;

    @Column(name = "Date")
    private LocalDate date = LocalDate.now();

    //Constructors
    public Offers() {
    }

    public Offers(String propertyId, String type, double amount, Date date) {
        PropertyId = propertyId;
        this.type = type;
        Amount = amount;
    }



    public Offers(String clientName, String propertyId, String type, double amount) {
        this.ClientNameField = clientName;
        this.PropertyId = propertyId;
        this.type = type;
        this.Amount = amount;
        this.date = LocalDate.now(); // You can set the current date automatically
    }


    //getters and setters


    public String getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(String propertyId) {
        PropertyId = propertyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

}
