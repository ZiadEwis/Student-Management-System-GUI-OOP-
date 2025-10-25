package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchUpdatePanel extends JPanel {
    private JTextField searchField, editId, editName, editAge, editDept, editGpa;
    private JComboBox<String> editGender;
    private JTable table;
    private StudentTableModel model;

    public SearchUpdatePanel(StudentManager manager) {
        setLayout(new BorderLayout());
        setBackground(new Color(245,245,247));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        top.setBackground(getBackground());
        searchField = new JTextField(25);
        JButton searchBtn = new JButton("Search (by ID or Name)");
        styleButton(searchBtn);
        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(searchBtn);
        add(top, BorderLayout.NORTH);

        model = new StudentTableModel(manager.getStudents());
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createTitledBorder("Search Results"));
        add(sp, BorderLayout.CENTER);

        JPanel editPanel = new JPanel(new GridLayout(6,2,10,10));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit Student"));
        editPanel.setBackground(new Color(250,250,252));

        editId = new JTextField(); editId.setEditable(false);
        editName = new JTextField();
        editAge = new JTextField();
        editGender = new JComboBox<>(new String[]{"Male", "Female"});
        editDept = new JTextField();
        editGpa = new JTextField();

        editPanel.add(new JLabel("ID:")); editPanel.add(editId);
        editPanel.add(new JLabel("Name:")); editPanel.add(editName);
        editPanel.add(new JLabel("Age:")); editPanel.add(editAge);
        editPanel.add(new JLabel("Gender:")); editPanel.add(editGender);
        editPanel.add(new JLabel("Department:")); editPanel.add(editDept);
        editPanel.add(new JLabel("GPA:")); editPanel.add(editGpa);

        JButton loadBtn = new JButton("Load Selected");
        JButton saveBtn = new JButton("Save Changes");
        styleButton(loadBtn); styleButton(saveBtn);

        JPanel buttons = new JPanel();
        buttons.setBackground(getBackground());
        buttons.add(loadBtn);
        buttons.add(saveBtn);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(editPanel, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        searchBtn.addActionListener(e -> doSearch(manager));
        searchField.addActionListener(e -> doSearch(manager));

        loadBtn.addActionListener(e -> loadSelected());
        saveBtn.addActionListener(e -> saveChanges(manager));
    }

    private void doSearch(StudentManager manager) {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            model.setData(manager.getStudents());
            return;
        }
        try {
            int id = Integer.parseInt(query);
            Student s = manager.searchStudent(id);
            if (s != null) model.setData(List.of(s));
            else {
                model.setData(List.of());
                JOptionPane.showMessageDialog(this, "No student found with that ID.");
            }
        } catch (NumberFormatException e) {
            List<Student> res = manager.searchByName(query);
            if (res.isEmpty())
                JOptionPane.showMessageDialog(this, "No students found with that name.");
            model.setData(res);
        }
    }

    private void loadSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first!");
            return;
        }
        Student s = model.getStudentAt(row);
        editId.setText(String.valueOf(s.getStudentID()));
        editName.setText(s.getFullName());
        editAge.setText(String.valueOf(s.getAge()));
        editGender.setSelectedItem(s.getGender());
        editDept.setText(s.getDepartment());
        editGpa.setText(String.valueOf(s.getGPA()));
    }

    private void saveChanges(StudentManager manager) {
        if (editId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Load a student first!");
            return;
        }
        String name = editName.getText().trim();
        String ageStr = editAge.getText().trim();
        String dept = editDept.getText().trim();
        String gpaStr = editGpa.getText().trim();
        String gender = (String) editGender.getSelectedItem();

        String err = validateInputs(name, ageStr, dept, gpaStr);
        if (err != null) {
            JOptionPane.showMessageDialog(this, err, "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(editId.getText());
        int age = Integer.parseInt(ageStr);
        double gpa = Double.parseDouble(gpaStr);

        Student updated = new Student(id, name, age, gender, dept, (float) gpa);
        manager.updateStudent(updated.getStudentID(),updated.getFullName(),updated.getAge(),updated.getGender(),updated.getDepartment(),updated.getGPA());
        model.setData(manager.getStudents());
        JOptionPane.showMessageDialog(this, "Student updated successfully!");
    }

    private String validateInputs(String name, String ageStr, String dept, String gpaStr) {
        if (name.isEmpty() || ageStr.isEmpty() || dept.isEmpty() || gpaStr.isEmpty())
            return "Please fill all fields.";
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 1 || age > 150) return "Age must be 1–150.";
        } catch (NumberFormatException e) {
            return "Age must be a number.";
        }
        try {
            double gpa = Double.parseDouble(gpaStr);
            if (gpa < 0 || gpa > 4) return "GPA must be between 0.0–4.0.";
        } catch (NumberFormatException e) {
            return "GPA must be numeric.";
        }
        return null;
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(0,153,204));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }
}

