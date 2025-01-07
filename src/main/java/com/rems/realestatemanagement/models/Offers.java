package com.rems.realestatemanagement.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Client_Name", length = 255, nullable = false)
    private String clientName;

    @Column(name = "Property_ID", length = 255, nullable = false)
    private String propertyId;

    @Column(name = "Type", length = 50, nullable = false)
    private String type;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "Date")
    private LocalDate date = LocalDate.now();

    // Default constructor
    public Offers() {
    }

    // Constructor
    public Offers(String clientName, String propertyId, String type, double amount) {
        this.clientName = clientName;
        this.propertyId = propertyId;
        this.type = type;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}