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

public class GrupView {
    @Getter
    private JPanel grupPanel;
    private JLabel studentiLabel;
    @Getter
    private JComboBox studentiComboBox;
    private JLabel profesorLabel;
    @Getter
    private JLabel profesorValue;
    @Getter
    private JButton createActivityButton;
    @Getter
    private JButton viewActivityButton;
    @Getter
    private JComboBox activityComboBox;
    @Getter
    private JButton prevButton;
    private JLabel numeGrup;
    @Getter
    private JLabel numeValue;
    @Getter
    private JButton inscriereActivityButton;
    @Getter
    private JComboBox inscriereActivityComboBox;
    @Getter
    private JButton reloadButton;
    private JLabel mesajeLabel;
    @Getter
    private JComboBox mesajComboBox;
    @Getter
    private JButton createMesajButton;
    @Getter
    private JTextField mesajTextField;
    @Getter
    private JButton iesireGrupButton;


    public GrupView(User user, Integer grupId) {
        grupPanel = new JPanel();
        grupPanel.setLayout(new GridLayoutManager(13, 4, new Insets(0, 0, 0, 0), -1, -1));
        studentiLabel = new JLabel();
        studentiLabel.setText("Studenti:");
        grupPanel.add(studentiLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentiComboBox = new JComboBox();
        grupPanel.add(studentiComboBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        profesorLabel = new JLabel();
        profesorLabel.setText("Profesor:");
        grupPanel.add(profesorLabel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        profesorValue = new JLabel();
        profesorValue.setText("Label");
        grupPanel.add(profesorValue, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        grupPanel.add(spacer1, new GridConstraints(11, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        grupPanel.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        grupPanel.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        grupPanel.add(spacer4, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        viewActivityButton = new JButton();
        viewActivityButton.setText("View Activity");
        grupPanel.add(viewActivityButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createActivityButton = new JButton();
        createActivityButton.setText("Create Activity");
        grupPanel.add(createActivityButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        activityComboBox = new JComboBox();
        grupPanel.add(activityComboBox, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        prevButton = new JButton();
        prevButton.setText("Prev");
        grupPanel.add(prevButton, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        grupPanel.add(spacer5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        numeGrup = new JLabel();
        numeGrup.setText("Nume grup:");
        grupPanel.add(numeGrup, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numeValue = new JLabel();
        numeValue.setText("Label");
        grupPanel.add(numeValue, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inscriereActivityButton = new JButton();
        inscriereActivityButton.setText("Inscriere Activity");
        grupPanel.add(inscriereActivityButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inscriereActivityComboBox = new JComboBox();
        grupPanel.add(inscriereActivityComboBox, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reloadButton = new JButton();
        reloadButton.setText("Reload");
        grupPanel.add(reloadButton, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mesajeLabel = new JLabel();
        mesajeLabel.setText("Mesaje:");
        grupPanel.add(mesajeLabel, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mesajComboBox = new JComboBox();
        grupPanel.add(mesajComboBox, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createMesajButton = new JButton();
        createMesajButton.setText("Create Mesaj");
        grupPanel.add(createMesajButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mesajTextField = new JTextField();
        grupPanel.add(mesajTextField, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        iesireGrupButton = new JButton();
        iesireGrupButton.setText("Iesire Grup");
        grupPanel.add(iesireGrupButton, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.init(user);

        numeValue.setText(dataBaseConnection.getGrupNume(user, grupId));

        String[] studentNume = dataBaseConnection.getGrupStudentEmail(user, grupId);
        for(Integer i = 1; i < parseInt(studentNume[0]); i++) {
            studentiComboBox.addItem(studentNume[i]);
        }

        String profesorNume = dataBaseConnection.getGrupProfesorEmail(user, grupId);
        if(profesorNume != null) {
            profesorValue.setText(profesorNume);
        }

        String[] activitiesNume = dataBaseConnection.getGrupActivitiesNume(user, grupId);
        for(Integer i = 1; i < parseInt(activitiesNume[0]); i++) {
            activityComboBox.addItem(activitiesNume[i]);
        }

        String[] activitiesNume1 = dataBaseConnection.getActivitiesNume(user, grupId);
        for(Integer i = 1; i < parseInt(activitiesNume1[0]); i++) {
            inscriereActivityComboBox.addItem(activitiesNume1[i]);
        }

        String[] mesaje = dataBaseConnection.getMesaj(user, grupId);
        for(Integer i = 1; i < parseInt(mesaje[0]); i++) {
            mesajComboBox.addItem(mesaje[i]);
        }
    }
}
