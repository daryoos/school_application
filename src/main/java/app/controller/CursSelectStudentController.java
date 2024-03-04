package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CursSelectStudentView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CursSelectStudentController {
    public CursSelectStudentView cursSelectViewStudent;

    public void startLogic(User user) {
        cursSelectViewStudent = new CursSelectStudentView(user);

        GUIFrameSinglePointAccess.changePanel(cursSelectViewStudent.getCursSelectPanel(), "Curs Select");

        cursSelectViewStudent.getVizualizareButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursStudentController cursStudentController = new CursStudentController();

                String cursSelected = cursSelectViewStudent.getVizualizareComboBox().getItemAt(cursSelectViewStudent.getVizualizareComboBox().getSelectedIndex()).toString();

                cursStudentController.startLogic(user, cursSelected);
            }
        });

        cursSelectViewStudent.getInscriereButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();

                String cursSelected = cursSelectViewStudent.getInscriereComboBox().getItemAt(cursSelectViewStudent.getInscriereComboBox().getSelectedIndex()).toString();
                dataBaseConnection.inscriereCurs(user, cursSelected);

                cursSelectViewStudent.getInscriereComboBox().removeItem(cursSelected);
                cursSelectViewStudent.getVizualizareComboBox().addItem(cursSelected);
                cursSelectViewStudent.getIesireComboBox().addItem(cursSelected);
            }
        });

        cursSelectViewStudent.getIesireButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();

                String cursSelected = cursSelectViewStudent.getIesireComboBox().getItemAt(cursSelectViewStudent.getIesireComboBox().getSelectedIndex()).toString();
                dataBaseConnection.iesireCurs(user, cursSelected);

                cursSelectViewStudent.getIesireComboBox().removeItem(cursSelected);
                cursSelectViewStudent.getIesireComboBox().addItem(cursSelected);
            }
        });

        cursSelectViewStudent.getActualizareButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursSelectStudentController cursSelectStudentController = new CursSelectStudentController();
                cursSelectStudentController.startLogic(user);
            }
        });

        cursSelectViewStudent.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
