package ant.rentathing.controllers;

import java.io.IOException;

public abstract class AuthController extends BaseController {

    public abstract void handleChangeWindowButton();

    public abstract String getUsername();
    public abstract String getPassword();
    public abstract void authProcess(String username, String password) throws IOException;

    public void handleSubmitButton() throws IOException {
        authProcess(getUsername(), getPassword());
    }
}
