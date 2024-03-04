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

public class ActivitateProfesorView {
    @Getter
    private JPanel activitatePanel;
    private JLabel studentLabel;
    @Getter
    private JComboBox studentComboBox;
    @Getter
    private JTextField notaTextField;
    @Getter
    private JButton adaugareNotaButton;
    @Getter
    private JButton prevButton;
    @Getter
    private JTextField procentajTextField;
    @Getter
    private JButton procentajButton;
    @Getter
    private JButton addStudentButton;
    @Getter
    private JComboBox addStudentComboBox;

    public ActivitateProfesorView(User user, Integer idCurs, Integer idActivitate) {
        activitatePanel = new JPanel();
        activitatePanel.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        studentLabel = new JLabel();
        studentLabel.setText("Student:");
        activitatePanel.add(studentLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentComboBox = new JComboBox();
        activitatePanel.add(studentComboBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        notaTextField = new JTextField();
        activitatePanel.add(notaTextField, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        adaugareNotaButton = new JButton();
        adaugareNotaButton.setText("Adaugare Nota");
        activitatePanel.add(adaugareNotaButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        activitatePanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        activitatePanel.add(spacer2, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        activitatePanel.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        activitatePanel.add(spacer4, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        prevButton = new JButton();
        prevButton.setText("Prev");
        activitatePanel.add(prevButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        procentajTextField = new JTextField();
        activitatePanel.add(procentajTextField, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        procentajButton = new JButton();
        procentajButton.setText("Procentaj");
        activitatePanel.add(procentajButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addStudentButton = new JButton();
        addStudentButton.setText("Add Student");
        activitatePanel.add(addStudentButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addStudentComboBox = new JComboBox();
        activitatePanel.add(addStudentComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        String[] students = dataBaseConnection.getStudentsInActivitateEmail(user, idActivitate);
        for(Integer i = 1; i < parseInt(students[0]); i++) {
            studentComboBox.addItem(students[i]);
        }

        procentajTextField.setText(dataBaseConnection.getProcentaj(user, idActivitate).toString());

        String[] addStudents = dataBaseConnection.getStudentsFromCursEmail(user, idCurs, idActivitate);
        for(Integer i = 1; i < parseInt(addStudents[0]); i++) {
            addStudentComboBox.addItem(addStudents[i]);
        }
    }
}
