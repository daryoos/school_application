package app.controller;

import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.ActivitateView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivitateController {
    ActivitateView activitateView;

    public void startLogic(User user, String curs, String tip) {
        activitateView = new ActivitateView(user, curs, tip);

        GUIFrameSinglePointAccess.changePanel(activitateView.getActivitatePanel(), "Activitate");

        activitateView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursStudentController cursStudentController = new CursStudentController();
                cursStudentController.startLogic(user, curs);
            }
        });
    }
}
