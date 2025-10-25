package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPanel extends JPanel implements ActionListener {

    AddStudentPanel() {

        // Name field
        JPanel NamePanel = new JPanel();
        NamePanel.setSize(300, 50);
        JLabel Name = new JLabel("Name");
        Name.setSize(150, 30);
        NamePanel.add(Name);
        JTextField NameField = new JTextField();
        NameField.setColumns(75);
        NamePanel.add(NameField);
        NameField.setPreferredSize(new Dimension(100, 75));

        // Age field
        JPanel AgePanel = new JPanel();
        JLabel age = new JLabel("Age");
        AgePanel.add(age);
        JTextField AgeField = new JTextField();
        AgeField.setColumns(75);
        AgeField.setPreferredSize(new Dimension(100, 75));
        AgePanel.add(AgeField);

        // Gender field
        JPanel GenderPanel = new JPanel();
        JComboBox<String> gender = new JComboBox<>();
        gender.setModel(new DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        GenderPanel.add(gender);
        setLayout(new GridLayout(2, 1, 10, 1));
        this.add(NamePanel);
        this.add(AgePanel);
        this.add(GenderPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
