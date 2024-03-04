package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CursProfesorView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class CursProfesorController {
    CursProfesorView cursViewProfesor;

    public void startLogic(User user, Integer idCurs) {
        cursViewProfesor = new CursProfesorView(user, idCurs);

        GUIFrameSinglePointAccess.changePanel(cursViewProfesor.getCursView(), "Curs");

        cursViewProfesor.getCursButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer idActivitate = dataBaseConnection.getActivitateId1(user, idCurs, "curs");

                ActivitateProfesorController activitateProfesorController = new ActivitateProfesorController();
                activitateProfesorController.startLogic(user, idCurs, idActivitate);
            }
        });

        cursViewProfesor.getSeminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer idActivitate = dataBaseConnection.getActivitateId1(user, idCurs, "seminar");

                ActivitateProfesorController activitateProfesorController = new ActivitateProfesorController();
                activitateProfesorController.startLogic(user, idCurs, idActivitate);
            }
        });

        cursViewProfesor.getLaboratorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer idActivitate = dataBaseConnection.getActivitateId1(user, idCurs, "laborator");

                ActivitateProfesorController activitateProfesorController = new ActivitateProfesorController();
                activitateProfesorController.startLogic(user, idCurs, idActivitate);
            }
        });

        cursViewProfesor.getAddStudentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                String[] studentEmail = dataBaseConnection.getStudentsNotInCursEmail(user, idCurs);
                Integer[] studentId = dataBaseConnection.getStudentsNotInCursId(user, idCurs);

                Integer i;
                for(i = 1; i < parseInt(studentEmail[0]); i++) {
                    if(studentEmail[i].equals(cursViewProfesor.getStudentComboBox().getSelectedItem().toString())) {
                        break;
                    }
                }

                dataBaseConnection.addStudentCurs(user, studentId[i], idCurs);

                CursProfesorController cursProfesorController = new CursProfesorController();
                cursProfesorController.startLogic(user, idCurs);
            }
        });

        cursViewProfesor.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursSelectProfesorController cursSelectProfesorController = new CursSelectProfesorController();
                cursSelectProfesorController.startLogic(user);
            }
        });
    }
}
