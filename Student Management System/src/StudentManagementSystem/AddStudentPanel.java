package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPanel extends JPanel implements ActionListener {

    AddStudentPanel() {
        // Name field
        JPanel NamePanel = new JPanel();
        NamePanel.setSize(30, 50);
        JLabel Name = new JLabel("Name");
        NamePanel.add(Name);
        JTextField NameField = new JTextField();
        NameField.setColumns(30);
        NamePanel.add(NameField);
        NameField.setPreferredSize(new Dimension(30, 30));

        // Age field
        JPanel AgePanel = new JPanel();
        JLabel age = new JLabel("Age");
        AgePanel.add(age);
        JTextField AgeField = new JTextField();
        AgeField.setColumns(30);
        AgeField.setPreferredSize(new Dimension(30, 30));
        AgePanel.add(AgeField);

        // Gender field
        JPanel GenderPanel = new JPanel();
        JLabel genderLabel = new JLabel("Gender");
        GenderPanel.add(genderLabel);
        JComboBox<String> gender = new JComboBox<>();
        gender.setModel(new DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        GenderPanel.add(gender);


        // Department field
        JPanel DepartmentPanel = new JPanel();
        JTextField departmentField = new JTextField();
        JLabel department = new JLabel("Department");
        DepartmentPanel.add(department);
        departmentField.setColumns(30);
        departmentField.setPreferredSize(new Dimension(30, 30));
        DepartmentPanel.add(departmentField);

        // GPA field
        JPanel GPAPanel = new JPanel();
        JLabel GPA = new JLabel("GPA");
        GPAPanel.add(GPA);
        JTextField GPAField = new JTextField();
        GPAField.setColumns(30);
        GPAField.setPreferredSize(new Dimension(30, 30));
        GPAPanel.add(GPAField);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(NamePanel);
        this.add(AgePanel );
        this.add(GenderPanel);
        this.add(DepartmentPanel);
        this.add(GPAPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
