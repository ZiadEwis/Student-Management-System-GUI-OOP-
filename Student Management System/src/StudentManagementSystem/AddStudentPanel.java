package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddStudentPanel extends JPanel implements ActionListener {
    JButton AddButton;
    JTextField NameField;
    JTextField AgeField;
    JComboBox<String> gender;
    JTextField departmentField;
    JTextField GPAField;
    JPanel ButtonPanel;
    JTextField IDField;
    AddStudentPanel() {
        // Common font
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);

        // ID field
        JPanel IDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel IDLabel = new JLabel("ID");
        IDLabel.setFont(fieldFont);
        IDField = new JTextField(20);
        IDField.setFont(fieldFont);
        IDPanel.add(IDLabel);
        IDPanel.add(IDField);

        // Name field
        JPanel NamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel Name = new JLabel("Name");
        Name.setFont(fieldFont);
        NameField = new JTextField(20);
        NameField.setFont(fieldFont);
        NamePanel.add(Name);
        NamePanel.add(NameField);

        // Age field
        JPanel AgePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel age = new JLabel("Age");
        age.setFont(fieldFont);
        AgeField = new JTextField(20);
        AgeField.setFont(fieldFont);
        AgePanel.add(age);
        AgePanel.add(AgeField);

        // Gender field
        JPanel GenderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(fieldFont);
        gender = new JComboBox<>(new String[] { "Male", "Female" });
        gender.setFont(fieldFont);
        GenderPanel.add(genderLabel);
        GenderPanel.add(gender);

        // Department field
        JPanel DepartmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel department = new JLabel("Department");
        department.setFont(fieldFont);
        departmentField = new JTextField(20);
        departmentField.setFont(fieldFont);
        DepartmentPanel.add(department);
        DepartmentPanel.add(departmentField);

        // GPA field
        JPanel GPAPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel GPA = new JLabel("GPA");
        GPA.setFont(fieldFont);
        GPAField = new JTextField(20);
        GPAField.setFont(fieldFont);
        GPAPanel.add(GPA);
        GPAPanel.add(GPAField);

        // Submit button
        AddButton = new JButton("Add Student");
        AddButton.addActionListener(this);
        AddButton.setFocusable(false);
        AddButton.setPreferredSize(new Dimension(180, 45));
        AddButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        ButtonPanel.add(AddButton);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        add(IDPanel);
        add(NamePanel);
        add(AgePanel);
        add(GenderPanel);
        add(DepartmentPanel);
        add(GPAPanel);
        add(Box.createVerticalStrut(20));
        add(ButtonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == AddButton){
            String studentName = NameField.getText().trim();
            String studentAge = AgeField.getText().trim();
            String studentGender = gender.getSelectedItem().toString();
            String studentDepartment = departmentField.getText().trim();
            String studentGPA = GPAField.getText().trim();
            String studentID = IDField.getText().trim();
            int age;
            double gpa;
            int id;
            if(studentName.isEmpty()||
                    studentAge.isEmpty() ||
                    studentGender.isEmpty() ||
                    studentDepartment.isEmpty() ||
                    studentGPA.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,
                            "Please fill in all fields!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            try {
                age = Integer.parseInt(studentAge);
                if (age <= 0) {
                    JOptionPane.showMessageDialog(this, "Age cannot be negative", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                gpa = Double.parseDouble(studentGPA);
                if (gpa < 0.0 || gpa > 4.0) {
                    JOptionPane.showMessageDialog(this, "GPA must be between 0.0 and 4.0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid GPA", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!isValidString(studentName)){
                JOptionPane.showMessageDialog(this, "Name can only contain letters and spaces", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!isValidString(studentDepartment)){
                JOptionPane.showMessageDialog(this, "Department can only contain letters and spaces", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(studentID.isEmpty())
            {
                id = generateUniqueID("Students.txt");
            }
            else{
                try {
                    id = Integer.parseInt(studentAge);
                    if (id <= 0) {
                        JOptionPane.showMessageDialog(this, "ID cannot be negative", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID must be integer", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Student s = new Student(id, studentName, age, studentGender, studentDepartment, gpa);
            manager.addStudent(s);
        }
    }
    private boolean isValidString(String s) {
        if (s == null || s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
    private int generateUniqueID(String filePath) {
        int maxID = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    if (id > maxID) {
                        maxID = id;
                    }
                } catch (NumberFormatException e) {
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }

        return maxID + 1;
    }

}
