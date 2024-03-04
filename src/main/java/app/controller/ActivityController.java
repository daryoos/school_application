package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.ActivityView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityController {
    ActivityView activityView;

    public void startLogic(User user, Integer idGrup, Integer idActivity) {
        activityView = new ActivityView(user, idActivity);

        GUIFrameSinglePointAccess.changePanel(activityView.getActivityPanel(), "Activity");

        activityView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupController grupController = new GrupController();
                grupController.startLogic(user, idGrup);
            }
        });
    }
}
