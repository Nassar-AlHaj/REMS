package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.Role;

public interface RoleDOA {
    public void save(Role role);
    public Role findById(int id);
    Role findByName(String name);
}