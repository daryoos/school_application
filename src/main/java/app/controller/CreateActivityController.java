package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CreateActivityView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class CreateActivityController {
    public CreateActivityView createActivityView;
    Integer activitateId;

    public void startLogic(User user, Integer grupId) {
        createActivityView = new CreateActivityView();

        GUIFrameSinglePointAccess.changePanel(createActivityView.getCreateActivityPanel(), "Create Activity");

        createActivityView.getCreateActivityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer nrParticipanti = parseInt(createActivityView.getNrParticipantiValue().getText());
                String nume = createActivityView.getNumeValue().getText().toString();

                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                activitateId = dataBaseConnection.createActivity(user, grupId, nrParticipanti, nume);
                dataBaseConnection.addStudentActivity(user, activitateId);

                GrupController grupController = new GrupController();
                grupController.startLogic(user, grupId);
            }
        });

        createActivityView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupController grupController = new GrupController();
                grupController.startLogic(user, grupId);
            }
        });
    }
}
