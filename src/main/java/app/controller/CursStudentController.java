package app.controller;

import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CursStudentView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CursStudentController {
    public CursStudentView cursViewStudent;

    public void startLogic(User user, String cursSelected) {
        cursViewStudent = new CursStudentView();

        GUIFrameSinglePointAccess.changePanel(cursViewStudent.getCursPanel(), "Curs Selected");

        cursViewStudent.getCursButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActivitateController activitateController = new ActivitateController();

                activitateController.startLogic(user, cursSelected, "curs");
            }
        });

        cursViewStudent.getSeminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActivitateController activitateController = new ActivitateController();

                activitateController.startLogic(user, cursSelected, "seminar");
            }
        });

        cursViewStudent.getLaboratorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActivitateController activitateController = new ActivitateController();

                activitateController.startLogic(user, cursSelected, "laborator");
            }
        });

        cursViewStudent.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursSelectStudentController cursSelectStudentController = new CursSelectStudentController();
                cursSelectStudentController.startLogic(user);
            }
        });
    }
}
