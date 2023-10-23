package demo19102023_2.src.main.java.de.ait;



import demo19102023_2.src.main.java.de.ait.controllers.UserController;
import demo19102023_2.src.main.java.de.ait.controllers.UserControllerConsoleImp;
import demo19102023_2.src.main.java.de.ait.repositories.UserRepository;
import demo19102023_2.src.main.java.de.ait.repositories.UserRepositoryFileImpl;
import demo19102023_2.src.main.java.de.ait.services.UserService;
import demo19102023_2.src.main.java.de.ait.services.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        //UserRepository repository = new repositories.UserRepositoryListImpl();
        UserRepository repository = new UserRepositoryFileImpl("users.txt");
        UserService service = new UserServiceImpl(repository);
        UserController controller = new UserControllerConsoleImp(service);

        Scanner scanner = new Scanner(System.in);
        boolean exit=false;
        while (!exit) {
            System.out.println("Input: 1 - create new user, 2 - print all users, exit - finish program");
            String command = scanner.nextLine();
            switch (command){
                case "exit": exit=true;break;
                case "1": controller.create();break;
                case "2": controller.printAll();break;
            }
        }
    }

}
