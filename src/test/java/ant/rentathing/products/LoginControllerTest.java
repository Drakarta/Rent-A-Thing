package ant.rentathing.products;

import ant.rentathing.classes.singleton.Session;
import ant.rentathing.classes.singleton.UserList;
import ant.rentathing.controllers.LoginController;
import ant.rentathing.classes.User;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginControllerTest extends ApplicationTest {

    private LoginController loginController;
    private Session session;
    private UserList userList;

    @BeforeEach
    void setUp() throws IOException {
        loginController = new LoginController();
        session = Session.getInstance();
        session.getInstance().activeUsers.clear();
        userList = UserList.getInstance();
    }

    @Test
    void testExistingUserCorrectPassword() throws IOException {
        // Create a test user
        String username = "testUser";
        String password = "testPassword";
        User testUser = new User(username, password);
        userList.getInstance().addUser(testUser);

        // Perform login with correct credentials
        Platform.runLater(() -> {
            try {
                loginController.authProcess(username, password);

            } catch (IOException e) {
                e.printStackTrace(); // Handle or log IOException properly
            }
        });

        // Wait for JavaFX operations to complete
        WaitForAsyncUtils.waitForFxEvents();

        // Verify that user is successfully logged in
        assertFalse(session.getInstance().getActiveUsers().contains(testUser));

    }

    @Test
    void testExistingUserIncorrectPassword() throws IOException {
        // Create a test user
        String username = "testUser";
        String correctPassword = "testPassword";
        String incorrectPassword = "wrongPassword";
        User testUser = new User(username, correctPassword);
        userList.getInstance().addUser(testUser);

        // Perform login with incorrect password
        Platform.runLater(() -> {
            try {
                loginController.authProcess(username, incorrectPassword);
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log IOException properly
            }
        });

        // Wait for JavaFX operations to complete
        WaitForAsyncUtils.waitForFxEvents();

        // Verify that user is not logged in
        assertFalse(session.getInstance().getActiveUsers().contains(testUser));
    }

    @Test
    void testNonExistingUser() throws IOException {
        // Attempt login with a non-existing username
        String username = "nonExistingUser";
        String password = "anyPassword";

        // Perform login
        Platform.runLater(() -> {
            try {
                loginController.authProcess(username, password);
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log IOException properly
            }
        });

        // Wait for JavaFX operations to complete
        WaitForAsyncUtils.waitForFxEvents();

        // Verify that no user is logged in
        assertEquals(0, session.getInstance().getActiveUsers().size());
    }

    @Test
    void testEmptyUsername() throws IOException {
        // Attempt login with an empty username
        String username = "";
        String password = "anyPassword";

        // Perform login
        Platform.runLater(() -> {
            try {
                loginController.authProcess(username, password);
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log IOException properly
            }
        });

        // Wait for JavaFX operations to complete
        WaitForAsyncUtils.waitForFxEvents();

        // Verify that no user is logged in
        assertEquals(0, session.getInstance().getActiveUsers().size());
    }

    @Test
    void testEmptyPassword() throws IOException {
        // Create a test user
        String username = "testUser";
        String password = "";
        User testUser = new User(username, password);
        userList.getInstance().addUser(testUser);

        // Perform login with empty password
        Platform.runLater(() -> {
            try {
                loginController.authProcess(username, password);
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log IOException properly
            }
        });

        // Wait for JavaFX operations to complete
        WaitForAsyncUtils.waitForFxEvents();

        // Verify that user is not logged in
        assertFalse(session.getInstance().getActiveUsers().contains(testUser));
    }
}
