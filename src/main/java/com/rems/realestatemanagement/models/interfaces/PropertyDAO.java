package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.Property;

import java.util.List;

public interface PropertyDAO {

    int insert(Property property);
    void update(Property property);
    void delete(Property property);

    List<String> getAllLocations();

    void delete(int id);
    List<Property> getAllProperties();
    Property findProperty(Property property);
    Property getProperty(int id);
    void updateImage(int id, String imageUrl);
    void deleteImage(int id);
    String getImage(int id);
}