package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.AddStudentsActivityView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class AddStudentsActivityController {
    public AddStudentsActivityView addStudentsActivityView;

    public void startLogic(User user, Integer idActivitate) {
        addStudentsActivityView = new AddStudentsActivityView(user, idActivitate);

        GUIFrameSinglePointAccess.changePanel(addStudentsActivityView.getAddStudentsPanel(), "Add Students");

        /*DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        Integer[] studentsId = dataBaseConnection.getStudentsId(user);
        String[] studentsEmail = dataBaseConnection.getStudentsEmail(user);

        if(studentsEmail != null) {
            for(Integer i = 1; i < parseInt(studentsEmail[0]); i++) {
                addStudentsActivityView.getStudentComboBox().addItem(studentsEmail[i]);
            }
        }*/


        addStudentsActivityView.getAddStudentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer[] studentsId = dataBaseConnection.getStudentsId(user, idActivitate);
                String[] studentsEmail = dataBaseConnection.getStudentsEmail(user, idActivitate);

                String email = addStudentsActivityView.getStudentComboBox().getSelectedItem().toString();
                Integer i;
                for(i = 1; i < parseInt(studentsEmail[0]); i++) {
                    if(studentsEmail[i].equals(email)) {
                        break;
                    }
                }
                dataBaseConnection.grupAddStudent(user, idActivitate, studentsId[i]);

                AddStudentsActivityController addStudentsActivityController = new AddStudentsActivityController();
                addStudentsActivityController.startLogic(user, idActivitate);
            }
        });

        addStudentsActivityView.getNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProfesorActivityController addProfesorActivityController = new AddProfesorActivityController();
                addProfesorActivityController.startLogic(user, idActivitate);
            }
        });

        addStudentsActivityView.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
