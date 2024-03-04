package app.controller;

import app.DataBaseConnection;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.GrupSelectView;
import my_sql_tables.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class GrupSelectController {
    public GrupSelectView grupSelectView;

    public void startLogic(User user) {
        grupSelectView = new GrupSelectView(user);

        GUIFrameSinglePointAccess.changePanel(grupSelectView.getGrupSelectPanel(), "Grup Select");

        grupSelectView.getGrupViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = grupSelectView.getGrupViewComboBox().getSelectedItem().toString();

                DataBaseConnection dataBaseConnection = new DataBaseConnection();
                dataBaseConnection.init(user);

                if(user.getType().equals("student")) {
                    Integer[] grupsId = dataBaseConnection.getGrupsId(user);
                    String[] grupsNume = dataBaseConnection.getGrupsNume(user);

                    Integer i;
                    for(i = 1; i < parseInt(grupsNume[0]); i++) {
                        if(grupsNume[i].equals(nume)) {
                            break;
                        }
                    }

                    GrupController grupController = new GrupController();
                    grupController.startLogic(user, grupsId[i]);
                }
                if(user.getType().equals("profesor")) {
                    Integer[] grupsId = dataBaseConnection.getGrupsIdProfesor(user);
                    String[] grupsNume = dataBaseConnection.getGrupsNumeProfesor(user);

                    Integer i;
                    for(i = 1; i < parseInt(grupsNume[0]); i++) {
                        if(grupsNume[i].equals(nume)) {
                            break;
                        }
                    }

                    GrupController grupController = new GrupController();
                    grupController.startLogic(user, grupsId[i]);
                }
            }
        });
        grupSelectView.getCreateGrupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateGrupController createGrupController = new CreateGrupController();
                createGrupController.startLogic(user);
            }
        });
        grupSelectView.getPrevButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController appController = new AppController();
                appController.startLogic(user);
            }
        });
    }
}
