package demo19102023_2.src.main.java.de.ait.services;


import demo19102023_2.src.main.java.de.ait.model.User;

import java.util.List;

public interface UserService {
    public void createUser(String name, String email);
    public List<User> getAllUsers();
}
