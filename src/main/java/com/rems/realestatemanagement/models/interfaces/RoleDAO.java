package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.Role;

public interface RoleDAO {
    public void save(Role role);
    public Role findById(int id);
    Role findByName(String name);
}