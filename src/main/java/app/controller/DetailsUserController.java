package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.DetailsUserView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsUserController {
    public DetailsUserView detailsUserView;

    public void startLogic(User user) {
        detailsUserView = new DetailsUserView();

        GUIFrameSinglePointAccess.changePanel(detailsUserView.getDetailsPanel(), "Details");

        //System.out.println(user.getType() + " " + user.getNume() + " " + user.getPrenume() + " " + user.getEmail() + " " + user.getPassword() +
        //        " " + user.getId() + " " + user.getIban() + " " + user.getCnp() + " " + user.getAddress() + " " + user.getTelephone());

        detailsUserView.getNumeValue().setText(user.getNume());
        detailsUserView.getPrenumeValue().setText(user.getPrenume());
        detailsUserView.getAdresaValue().setText(user.getAddress());
        detailsUserView.getTelefonValue().setText(user.getTelephone());
        detailsUserView.getEmailValue().setText(user.getEmail());
        detailsUserView.getCnpValue().setText(user.getCnp());
        detailsUserView.getIbanValue().setText(user.getIban());
        detailsUserView.getParolaValue().setText(user.getPassword());

        detailsUserView.getApplyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();

                dataBaseConnection.init(user);

                String nume = detailsUserView.getNumeValue().getText().toString();
                String prenume = detailsUserView.getPrenumeValue().getText().toString();
                String adresa = detailsUserView.getAdresaValue().getText().toString();
                String telefon = detailsUserView.getTelefonValue().getText().toString();
                String email = detailsUserView.getEmailValue().getText().toString();
                String cnp = detailsUserView.getCnpValue().getText().toString();
                String iban = detailsUserView.getIbanValue().getText().toString();
                String parola = detailsUserView.getParolaValue().getText().toString();

                dataBaseConnection.updateDetails(user, nume, prenume, email, parola, iban, cnp, telefon, adresa);
            }
        });

        detailsUserView.getAvansatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getType().equals("student")) {
                    AvansatStudentController avansatStudentController = new AvansatStudentController();
                    avansatStudentController.startLogic(user);
                }
            }
        });

        detailsUserView.getPreviousButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
