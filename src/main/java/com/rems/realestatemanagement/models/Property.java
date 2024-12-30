package com.rems.realestatemanagement.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "propertyType", nullable = false)
    private String propertyType;

    @Column(name = "propertyName", nullable = false)
    private String propertyName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "numberOfRooms", nullable = false)
    private int numberOfRooms;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "imageProperty")
    private String imageProperty;

    @Column(name = "listingDate")
    private LocalDate listingDate;

    // Default constructor
    public Property() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageProperty() {
        return imageProperty;
    }

    public void setImageProperty(String imageProperty) {
        this.imageProperty = imageProperty;
    }

    public LocalDate getlistingDate() {
        return listingDate;
    }

    public void setlistingDate(LocalDate listingDate) {
        this.listingDate = listingDate;
    }
}