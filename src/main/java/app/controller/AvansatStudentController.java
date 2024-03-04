package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.AvansatStudentView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class AvansatStudentController {
    public AvansatStudentView avansatStudentView;

    public void startLogic(User user) {
        avansatStudentView = new AvansatStudentView();

        GUIFrameSinglePointAccess.changePanel(avansatStudentView.getAvansatPanel(), "Avansat");

        DataBaseConnection dataBaseConnection = new DataBaseConnection();

        dataBaseConnection.init(user);

        avansatStudentView.getAnValue().setText(dataBaseConnection.getAn(user).toString());
        avansatStudentView.getNumarOreValue().setText(dataBaseConnection.getNrOre(user).toString());

        avansatStudentView.getApplyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer an = parseInt(avansatStudentView.getAnValue().getText().toString());
                Integer nrOre = parseInt(avansatStudentView.getNumarOreValue().getText().toString());

                dataBaseConnection.updateAvansat(user, an, nrOre);
            }
        });

        avansatStudentView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetailsUserController detailsUserController = new DetailsUserController();
                detailsUserController.startLogic(user);
            }
        });
    }
}
