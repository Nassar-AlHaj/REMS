package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.User;

public interface UsersDAO {
    public void insert(User user);
    public User getUserByEmailAndPassword(String email, String password);
    public User getUserByEmail(String email);
    public void update(User user);
}
