/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class ViewStudentPanel extends JPanel 
{
    private StudentManager manager;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortCombo;
    private JButton refreshButton;
    private static final String[] COLUMNS = {"ID", "Full Name", "Age", "Gender", "Department", "GPA"};
    public ViewStudentPanel(StudentManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout(10, 10));

        
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        sortCombo = new JComboBox<>(new String[]{"Sort by ID", "Sort by Name"});
        refreshButton = new JButton("Refresh");

        
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Sort:"));
        top.add(sortCombo);
        top.add(refreshButton);

        
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        
        refreshButton.addActionListener(e -> refreshTable());
        sortCombo.addActionListener(e -> refreshTable());

        
        refreshTable();
    }

    
    private void refreshTable() {
        tableModel.setRowCount(0); 
        List<Student> students = manager.getAllStudents();

        String sortType = (String) sortCombo.getSelectedItem();
        if ("Sort by Name".equals(sortType)) {
            students.sort(Comparator.comparing(Student::getFullName, String.CASE_INSENSITIVE_ORDER));
        } else {
            students.sort(Comparator.comparingInt(Student::getId));
        }

        for (Student s : students) {
            Object[] row = { s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa() };
            tableModel.addRow(row);
        }
    }

}