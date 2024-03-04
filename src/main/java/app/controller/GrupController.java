package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.GrupView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class GrupController {
    public GrupView grupView;

    public void startLogic(User user, Integer grupId) {
        grupView = new GrupView(user, grupId);

        GUIFrameSinglePointAccess.changePanel(grupView.getGrupPanel(), "Grup");

        grupView.getIesireGrupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                dataBaseConnection.iesireGrup(user, grupId);

                GrupSelectController grupSelectController = new GrupSelectController();
                grupSelectController.startLogic(user);
            }
        });

        grupView.getCreateMesajButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                dataBaseConnection.createMesaj(user, grupId, grupView.getMesajTextField().getText());

                GrupController grupController = new GrupController();
                grupController.startLogic(user, grupId);
            }
        });

        grupView.getCreateActivityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateActivityController createActivityController = new CreateActivityController();
                createActivityController.startLogic(user, grupId);
            }
        });
        grupView.getViewActivityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer[] activitiesId = dataBaseConnection.getGrupActivitiesId(user, grupId);
                String[] activitiesNume = dataBaseConnection.getGrupActivitiesNume(user, grupId);

                String activity = grupView.getActivityComboBox().getItemAt(grupView.getActivityComboBox().getSelectedIndex()).toString();
                //System.out.println(activity);

                Integer i;
                for(i = 1; i < parseInt(activitiesNume[0]); i++) {
                    if(activitiesNume[i].equals(activity)) {
                        break;
                    }
                }
                ActivityController activityController = new ActivityController();
                activityController.startLogic(user, grupId, activitiesId[i]);
            }
        });

        grupView.getInscriereActivityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                Integer[] activitiesId = dataBaseConnection.getActivitiesId(user, grupId);
                String[] activitiesNume = dataBaseConnection.getActivitiesNume(user, grupId);

                Integer i;
                for(i = 1; i < parseInt(activitiesNume[0]); i++) {
                    if(activitiesNume[i].equals(grupView.getInscriereActivityComboBox().getSelectedItem().toString())) {
                        break;
                    }
                }

                dataBaseConnection.addStudentActivity(user, activitiesId[i]);

                GrupController grupController = new GrupController();
                grupController.startLogic(user, grupId);
            }
        });

        grupView.getReloadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupController grupController = new GrupController();
                grupController.startLogic(user, grupId);
            }
        });
        grupView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupSelectController grupSelectController = new GrupSelectController();
                grupSelectController.startLogic(user);
            }
        });
    }
}
