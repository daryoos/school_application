package app.view;

import app.DataBaseConnection;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import lombok.Getter;
import my_sql_tables.User;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class GrupSelectView {
    @Getter
    private JPanel grupSelectPanel;
    @Getter
    private JButton grupViewButton;
    @Getter
    private JComboBox grupViewComboBox;
    @Getter
    private JButton prevButton;
    @Getter
    private JButton createGrupButton;

    public GrupSelectView(User user) {
        grupSelectPanel = new JPanel();
        grupSelectPanel.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        grupViewButton = new JButton();
        grupViewButton.setText("Grup View");
        grupSelectPanel.add(grupViewButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        grupViewComboBox = new JComboBox();
        grupSelectPanel.add(grupViewComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        if(user.getType().equals("student")) {
            String[] grupsNume = dataBaseConnection.getGrupsNume(user);
            for(Integer i = 1; i < parseInt(grupsNume[0]); i++) {
                grupViewComboBox.addItem(grupsNume[i]);
            }
        }
        if(user.getType().equals("profesor")) {
            String[] grupsNume = dataBaseConnection.getGrupsNumeProfesor(user);
            for(Integer i = 1; i < parseInt(grupsNume[0]); i++) {
                grupViewComboBox.addItem(grupsNume[i]);
            }
        }

        prevButton = new JButton();
        prevButton.setText("Prev");
        grupSelectPanel.add(prevButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createGrupButton = new JButton();
        createGrupButton.setText("Create Grup");
        grupSelectPanel.add(createGrupButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        grupSelectPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        grupSelectPanel.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        grupSelectPanel.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        grupSelectPanel.add(spacer4, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        grupSelectPanel.add(spacer5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }
}
