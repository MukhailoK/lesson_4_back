package demo19102023_2.src.main.java.de.ait.controllers;


import demo19102023_2.src.main.java.de.ait.model.User;

import java.util.List;

public interface UserController {
    public void create();
    public List<User> getAll();
    public void printAll();
}
