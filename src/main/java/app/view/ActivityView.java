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

public class ActivityView {
    @Getter
    private JPanel activityPanel;
    private JLabel numeLabel;
    @Getter
    private JLabel numeValue;
    private JLabel participantiLabel;
    @Getter
    private JComboBox participantiComboBox;
    @Getter
    private JButton prevButton;

    public ActivityView(User user, Integer idActivity) {
        activityPanel = new JPanel();
        activityPanel.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        numeLabel = new JLabel();
        numeLabel.setText("Nume:");
        activityPanel.add(numeLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numeValue = new JLabel();
        numeValue.setText("Label");
        activityPanel.add(numeValue, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        participantiLabel = new JLabel();
        participantiLabel.setText("Participanti:");
        activityPanel.add(participantiLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        participantiComboBox = new JComboBox();
        activityPanel.add(participantiComboBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        activityPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        activityPanel.add(spacer2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        activityPanel.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        activityPanel.add(spacer4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        prevButton = new JButton();
        prevButton.setText("Prev");
        activityPanel.add(prevButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        numeValue.setText(dataBaseConnection.getActivityNume(user, idActivity));

        String[] participantiEmail = dataBaseConnection.getActivityParticipanti(user, idActivity);
        for(Integer i = 1; i < parseInt(participantiEmail[0]); i++) {
            participantiComboBox.addItem(participantiEmail[i]);
        }
    }
}
