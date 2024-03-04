package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CursSelectProfesorView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CursSelectProfesorController {
    CursSelectProfesorView cursSelectViewProfesor;

    public void startLogic(User user) {
        cursSelectViewProfesor = new CursSelectProfesorView(user);

        GUIFrameSinglePointAccess.changePanel(cursSelectViewProfesor.getCursSelect(), "Curs Select");

        cursSelectViewProfesor.getVizualizareCursButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursProfesorController cursProfesorController = new CursProfesorController();

                String cursSelected = cursSelectViewProfesor.getCursuriComboBox().getSelectedItem().toString();

                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                cursProfesorController.startLogic(user, dataBaseConnection.getIdCurs(user, cursSelected));
            }
        });

        cursSelectViewProfesor.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
