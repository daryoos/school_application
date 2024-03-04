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

public class CreateGrupView {
    @Getter
    private JPanel createActivityPanel;
    @Getter
    private JTextField numeActivitateValue;
    private JLabel numeActivitateLabel;
    @Getter
    private JComboBox activitateSelectComboBox;
    private JLabel activitateSelectLabel;
    @Getter
    private JComboBox cursSelectComboBox;
    private JLabel cursSelectLabel;
    @Getter
    private JButton nextButton;
    @Getter
    private JButton prevButton;

    public CreateGrupView() {
        createActivityPanel = new JPanel();
        createActivityPanel.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        numeActivitateValue = new JTextField();
        createActivityPanel.add(numeActivitateValue, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        numeActivitateLabel = new JLabel();
        numeActivitateLabel.setText("Nume activitate:");
        createActivityPanel.add(numeActivitateLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        activitateSelectComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("curs");
        defaultComboBoxModel1.addElement("seminar");
        defaultComboBoxModel1.addElement("laborator");
        activitateSelectComboBox.setModel(defaultComboBoxModel1);
        createActivityPanel.add(activitateSelectComboBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        activitateSelectLabel = new JLabel();
        activitateSelectLabel.setText("Activitate select:");
        createActivityPanel.add(activitateSelectLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cursSelectLabel = new JLabel();
        cursSelectLabel.setText("Curs select:");
        createActivityPanel.add(cursSelectLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cursSelectComboBox = new JComboBox();
        createActivityPanel.add(cursSelectComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        User user = new User();
        user.setType("root");
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        String[] cursNume = dataBaseConnection.getCursNume(user);
        for(Integer i = 1; i < parseInt(cursNume[0]); i++) {
            cursSelectComboBox.addItem(cursNume[i]);
        }

        final Spacer spacer1 = new Spacer();
        createActivityPanel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        createActivityPanel.add(spacer2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        createActivityPanel.add(spacer3, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        nextButton = new JButton();
        nextButton.setText("Next");
        createActivityPanel.add(nextButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        prevButton = new JButton();
        prevButton.setText("Prev");
        createActivityPanel.add(prevButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }
}
