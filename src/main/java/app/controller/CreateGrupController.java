package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CreateGrupView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class CreateGrupController {
    public CreateGrupView createActivityView;

    public void startLogic(User user) {
        createActivityView = new CreateGrupView();

        GUIFrameSinglePointAccess.changePanel(createActivityView.getCreateActivityPanel(), "Create Activity");

        createActivityView.getNextButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                String curs = createActivityView.getCursSelectComboBox().getSelectedItem().toString();
                String activitate = createActivityView.getActivitateSelectComboBox().getSelectedItem().toString();
                String nume = createActivityView.getNumeActivitateValue().getText().toString();

                String[] cursNume = dataBaseConnection.getCursNume(user);
                Integer[] cursId = dataBaseConnection.getCursId(user);

                Integer i;
                for(i = 0; i < parseInt(cursNume[0]); i++) {
                    if(cursNume[i].equals(curs)) {
                        break;
                    }
                }

                dataBaseConnection.createGrup(user, cursId[i], activitate, nume);
                Integer idGrup = dataBaseConnection.getGrupId(user);

                AddStudentsActivityController addStudentsActivityController = new AddStudentsActivityController();
                addStudentsActivityController.startLogic(user, idGrup);
            }
        });


        createActivityView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
