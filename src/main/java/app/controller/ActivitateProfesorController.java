package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.ActivitateProfesorView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class ActivitateProfesorController {
    ActivitateProfesorView activitateProfesorView;

    public void startLogic(User user, Integer idCurs, Integer idActivitate) {
        activitateProfesorView = new ActivitateProfesorView(user, idCurs, idActivitate);

        GUIFrameSinglePointAccess.changePanel(activitateProfesorView.getActivitatePanel(), "Activitate");

        activitateProfesorView.getAddStudentButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer[] idStudent = dataBaseConnection.getStudentsFromCursId(user, idCurs, idActivitate);
                String[] emailStudent = dataBaseConnection.getStudentsFromCursEmail(user, idCurs, idActivitate);

                Integer i;
                for(i = 1; i < parseInt(emailStudent[0]); i++) {
                    if(emailStudent[i].equals(activitateProfesorView.getAddStudentComboBox().getSelectedItem().toString())) {
                        break;
                    }
                }
                dataBaseConnection.addStudentActivitate(user, idStudent[i], idActivitate);

                ActivitateProfesorController activitateProfesorController = new ActivitateProfesorController();
                activitateProfesorController.startLogic(user, idCurs, idActivitate);
            }
        });

        activitateProfesorView.getAdaugareNotaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer[] idStudent = dataBaseConnection.getStudentsInActivitateId(user, idActivitate);
                String[] emailStudent = dataBaseConnection.getStudentsInActivitateEmail(user, idActivitate);

                Integer i;
                for(i = 1; i < parseInt(emailStudent[0]); i++) {
                    if(emailStudent[i].equals(activitateProfesorView.getStudentComboBox().getSelectedItem().toString())) {
                        break;
                    }
                }
                dataBaseConnection.addNotaStudent(user, idActivitate, idStudent[i], parseInt(activitateProfesorView.getNotaTextField().getText()));
            }
        });

        activitateProfesorView.getProcentajButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                dataBaseConnection.updateProcentaj(user, idActivitate, parseInt(activitateProfesorView.getProcentajTextField().getText()));

                ActivitateProfesorController activitateProfesorController = new ActivitateProfesorController();
                activitateProfesorController.startLogic(user, idCurs, idActivitate);
            }
        });
        activitateProfesorView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursProfesorController cursProfesorController = new CursProfesorController();
                cursProfesorController.startLogic(user, idCurs);
            }
        });
    }
}
