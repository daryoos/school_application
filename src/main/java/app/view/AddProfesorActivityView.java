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

public class AddProfesorActivityView {
    @Getter
    private JPanel addProfesorPanel;
    private JLabel selectProfesorLabel;
    @Getter
    private JComboBox profesorComboBox;
    @Getter
    private JButton finishButton;

    public AddProfesorActivityView(User user, Integer id_activitate) {
        addProfesorPanel = new JPanel();
        addProfesorPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        selectProfesorLabel = new JLabel();
        selectProfesorLabel.setText("Select Profesor:");
        addProfesorPanel.add(selectProfesorLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        profesorComboBox = new JComboBox();
        addProfesorPanel.add(profesorComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        Integer[] profesorId = dataBaseConnection.getProfesorId(user, id_activitate);
        String[] profesorEmail = dataBaseConnection.getProfesorEmail(user, id_activitate);

        if(profesorEmail != null) {
            for(Integer i = 1; i < parseInt(profesorEmail[0]); i++) {
                profesorComboBox.addItem(profesorEmail[i]);
            }
        }

        final Spacer spacer1 = new Spacer();
        addProfesorPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        addProfesorPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        addProfesorPanel.add(spacer3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        addProfesorPanel.add(spacer4, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setText("Finish");
        addProfesorPanel.add(finishButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }
}
