package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.Permissions;

public interface PermissionDOA {

    void save(Permissions permission);

    Permissions findById(int id);

    Permissions findByName(String name);
}
