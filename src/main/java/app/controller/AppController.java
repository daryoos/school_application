package app.controller;

import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.AppView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController {
    public AppView appView;

    public void startLogic(User user) {
        //System.out.println(user.getId() + "\n" + user.getNume() + "\n" + user.getPrenume());


        appView = new AppView();

        GUIFrameSinglePointAccess.changePanel(appView.getAppPanel(), "app");

        appView.getDetailsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetailsUserController detailsUserController = new DetailsUserController();
                detailsUserController.startLogic(user);
            }
        });

        appView.getCursSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getType().equals("student")) {
                    CursSelectStudentController cursSelectStudentController = new CursSelectStudentController();

                    cursSelectStudentController.startLogic(user);
                }
                if(user.getType().equals("profesor")) {
                    CursSelectProfesorController cursSelectProfesorController = new CursSelectProfesorController();

                    cursSelectProfesorController.startLogic(user);
                }
            }
        });

        appView.getGrupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupSelectController grupSelectController = new GrupSelectController();
                grupSelectController.startLogic(user);
            }
        });

        appView.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });
    }
}
