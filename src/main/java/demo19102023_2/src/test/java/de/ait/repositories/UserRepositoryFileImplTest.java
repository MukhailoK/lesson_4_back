package demo19102023_2.src.test.java.de.ait.repositories;

import demo19102023_2.src.main.java.de.ait.model.User;
import demo19102023_2.src.main.java.de.ait.repositories.UserRepository;
import demo19102023_2.src.main.java.de.ait.repositories.UserRepositoryFileImpl;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryFileImplTest {
    public static final String TEST_FILE = "users_test.txt";
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepositoryFileImpl(TEST_FILE);
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        try {
            boolean newFile = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("create file error");
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Test normal save")
    void save() throws Exception {
        // подготовка исзодных данных
        // ожидаемый рез.
        // вызываем тестируемый метод
        // сравнить ожилания и результат метода

        User user = new User("qwer", "qwer@qwert.com");
        String expected = "1;qwer;qwer@qwert.com";
        repository.save(user);

        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            String result = bf.readLine();
            assertEquals(expected, result);
        }
    }

    @Test
    @DisplayName("Test save duplicate User")
    void testSaveDuplicateUSer() {
        List<User> users = repository.findAll();
        assertEquals(Collections.emptyList(), users);

        User user = new User("qwer", "qwer@qwert.com");
        repository.save(user);
        repository.save(user);
        users = repository.findAll();
        assertEquals(user, users.get(0));
        assertEquals(1, users.size());
    }
    @Test
    @DisplayName("Test save User with existed Email")
    void testSaveUserWithExistedEmail() {
        List<User> users = repository.findAll();
        assertEquals(Collections.emptyList(), users);

        User user = new User("qwer", "qwer@qwert.com");
        repository.save(user);
        user = new User("Rewq", "qwer@qwert.com");
        repository.save(user);
        users = repository.findAll();
        assertEquals(user, users.get(0));
        assertEquals(1, users.size());
    }

    @Test
    @DisplayName("Test save wrong User")
    void testSaveWrongUSer() {
        User user = new User(null, null);
        repository.save(user);
        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            String result = bf.readLine();
            assertNull(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @DisplayName("Test save User with wrong E-Mail [@]")
    void testSaveUserWithWrongEmail() {
        User user = new User("Joker", "asdasdfad.asdasd.cor");
        repository.save(user);
        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            String result = bf.readLine();
            assertNull(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Test save User with wrong E-Mail [domain]")
    void testSaveUserWithWrongEmail2() {
        User user = new User("Joker", "asdasdfad@asdasd");
        repository.save(user);
        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            String result = bf.readLine();
            assertNull(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Test save User with wrong E-Mail [short]")
    void testSaveUserWithWrongEmail3() {
        User user = new User("Joker", "a@a.com");
        repository.save(user);
        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            String result = bf.readLine();
            assertNull(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test findByEmail for an existing email")
    void testFindByEmail() {
        User user = new User("qwer", "qwer@qwert.com");
        repository.save(user);
        User foundUser = repository.findByEmail("qwer@qwert.com");
        assertEquals(user, foundUser);
    }

    @Test
    @DisplayName("Test findByEmail for a non-existent email")
    void testFindNotExistUser() {
        User nonExistentUser = repository.findByEmail("nonexistent@example.com");
        assertNull(nonExistentUser);
    }

    @Test
    @DisplayName("Test find all users")
    void testFindAll() {
        List<User> users = repository.findAll();
        assertEquals(Collections.emptyList(), users);

        User user1 = new User(1L, "User1", "user1@example.com");
        User user2 = new User(2L, "User2", "user2@example.com");
        repository.save(user1);
        repository.save(user2);

        users = repository.findAll();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    @DisplayName("Test find all Users in empty file")
    void testFindInEmptyFile() {
        List<User> users = repository.findAll();
        assertEquals(Collections.emptyList(), users);
    }

    @Test
    @DisplayName("Test find by ID")
    void testFindByID() {
        User user = new User(1L, "TestUser", "test@example.com");
        repository.save(user);
        User foundUser = repository.findByID(1L);
        assertEquals(user, foundUser);
    }

    @Test
    @DisplayName("Test find a non-existent user")
    void festFindByIdNotExistUser() {
        User nonExistentUser = repository.findByID(2L);
        assertNull(nonExistentUser);
    }


}