package com.rems.realestatemanagement.models;

public class Property {
    private String title;
    private String location;
    private double price;
    private double size;
    private String type;
    private boolean available;

    public Property(String title, String location, double price, double size, String type, boolean available) {
        this.title = title;
        this.location = location;
        this.price = price;
        this.size = size;
        this.type = type;
        this.available = available;
    }

    // Getters
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public double getSize() { return size; }
    public String getType() { return type; }
    public boolean isAvailable() { return available; }

    // Property toString for display
    @Override
    public String toString() {
        return title + " - " + location + " ($" + price + ", " + size + " sqm, " + type + ")";
    }
}
