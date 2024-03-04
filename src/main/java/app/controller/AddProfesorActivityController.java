package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.AddProfesorActivityView;
import app.view.AddStudentsActivityView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class AddProfesorActivityController {
    public AddProfesorActivityView addProfesorActivityView;

    public void startLogic(User user, Integer idActivitate) {
        addProfesorActivityView = new AddProfesorActivityView(user, idActivitate);

        GUIFrameSinglePointAccess.changePanel(addProfesorActivityView.getAddProfesorPanel(), "Add Profesor");

        /*DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        Integer[] profesorId = dataBaseConnection.getProfesorId(user);
        String[] profesorEmail = dataBaseConnection.getProfesorEmail(user);

        if(profesorEmail != null) {
            for(Integer i = 0; i < parseInt(profesorEmail[0]); i++) {
                addProfesorActivityView.getProfesorComboBox().addItem(profesorEmail);
            }
        }*/

        addProfesorActivityView.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer[] profesorId = dataBaseConnection.getProfesorId(user, idActivitate);
                String[] profesorEmail = dataBaseConnection.getProfesorEmail(user, idActivitate);

                String email = addProfesorActivityView.getProfesorComboBox().getSelectedItem().toString();
                Integer i;
                for(i = 1; i < parseInt(profesorEmail[0]); i++) {
                    if(profesorEmail[i].equals(email)) {
                        break;
                    }
                }
                dataBaseConnection.grupAddProfesor(user, idActivitate, profesorId[i]);

                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
