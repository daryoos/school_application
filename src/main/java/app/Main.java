package app;

import app.controller.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginController logInController = new LoginController();
        logInController.startLogic();
    }
}