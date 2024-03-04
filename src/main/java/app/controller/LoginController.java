package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.LoginView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    public void startLogic() {
        loginView = new LoginView();

        GUIFrameSinglePointAccess.changePanel(loginView.getLoginPanel(), "Login");

        loginView.getLogInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    User user = new User();

                    user.setType(loginView.getUserComboBox().getItemAt(loginView.getUserComboBox().getSelectedIndex()).toString());

                    user.setEmail(loginView.getTextFieldEmail().getText());
                    user.setPassword(String.valueOf(loginView.getPasswordField().getPassword()));

                    DataBaseConnection dataBaseConnection = new DataBaseConnection();

                    dataBaseConnection.init(user);

                    if(dataBaseConnection.verifyLogin(user)) {
                        AppController appController = new AppController();
                        appController.startLogic(user);
                        System.out.println("login trecut");
                    }
                } catch (Exception ex) {
                    System.out.println("\nLog eroare in log in : " + ex);
                }
            }
        });

        loginView.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterController registerController = new RegisterController();
                registerController.startLogic();
            }
        });
    }

}
