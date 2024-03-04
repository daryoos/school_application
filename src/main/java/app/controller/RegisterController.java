package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.RegisterView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    private RegisterView registerView;
    public void startLogic() {
        registerView = new RegisterView();

        GUIFrameSinglePointAccess.changePanel(registerView.getRegisterPanel(), "Register");

        registerView.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setType("student");

                DataBaseConnection dataBaseConnection = new DataBaseConnection();

                dataBaseConnection.init(user);

                String nume = registerView.getNumeValue().getText().toString();
                String prenume = registerView.getPrenumeValue().getText().toString();
                String adresa = registerView.getAdresaValue().getText().toString();
                String telefon = registerView.getTelefonValue().getText().toString();
                String email = registerView.getEmailValue().getText().toString();
                String cnp = registerView.getCnpValue().getText().toString();
                String iban = registerView.getIbanValue().getText().toString();
                String parola = registerView.getParolaValue().getText().toString();
                Integer an = Integer.parseInt(registerView.getAnValue().getText().toString());
                Integer nr_ore = Integer.parseInt(registerView.getNumarOreValue().getText().toString());

                dataBaseConnection.register(user, nume, prenume, email, parola, iban, cnp, telefon, adresa, an, nr_ore);

                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });

        registerView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });
    }

}
