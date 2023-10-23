package demo19102023_2.src.main.java.de.ait.repositories;


import demo19102023_2.src.main.java.de.ait.model.User;

public interface UserRepository extends CrudRepository<User>{
    public User findByEmail(String email);
}
