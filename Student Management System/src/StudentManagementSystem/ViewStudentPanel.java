package StudentManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List; 
import java.util.ArrayList; 


public class ViewStudentPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortByBox;
    private JComboBox<String> orderBox;
    private JButton sortButton;
    private JLabel note;
    private StudentManager manager;

    public ViewStudentPanel(StudentManager manager) {
        this.manager = manager;
        
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 247));

        // Sorting Options
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(getBackground());
        topPanel.add(new JLabel("Sort by:"));

        sortByBox = new JComboBox<>(new String[]{"ID", "Name"});
        orderBox = new JComboBox<>(new String[]{"Ascending", "Descending"});
        sortButton = new JButton("Sort");

        sortButton.setBackground(new Color(0, 153, 204));
        sortButton.setForeground(Color.BLACK);
        sortButton.setFocusPainted(false);
        sortButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        topPanel.add(sortByBox);
        topPanel.add(orderBox);
        topPanel.add(sortButton);
        add(topPanel, BorderLayout.NORTH);

        // Table 
        String[] columns = {"ID", "Full Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("All Students"));
        add(scrollPane, BorderLayout.CENTER);

        // ===== Bottom Note =====
        int studentCount = manager.getStudents().size();
        note = new JLabel("Total Students: " + studentCount, SwingConstants.RIGHT);
        note.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(note, BorderLayout.SOUTH);

        loadTable();

        //Sort Button Action
        sortButton.addActionListener(e -> {
            sortStudents();
        });
    }

    private void sortStudents() {
        // Create a new list from the manager's students
        List<Student> list = new ArrayList<>(manager.getStudents());
        
        String sortBy = (String) sortByBox.getSelectedItem();
        String order = (String) orderBox.getSelectedItem();

        // Create comparator based on selection
        if ("Name".equals(sortBy)) {
            if ("Ascending".equals(order)) {
                list.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getFullName().compareToIgnoreCase(s2.getFullName());
                    }
                });
            } else {
                list.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s2.getFullName().compareToIgnoreCase(s1.getFullName());
                    }
                });
            }
        } else { // Sort by ID
            if ("Ascending".equals(order)) {
                list.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return Integer.compare(s1.getStudentID(), s2.getStudentID());
                    }
                });
            } else {
                list.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return Integer.compare(s2.getStudentID(), s1.getStudentID());
                    }
                });
            }
        }

        loadTable(list);
        note.setText("Total Students: " + list.size());
    }

    private void loadTable() {
        loadTable(manager.getStudents());
    }

    private void loadTable(List<Student> students) {
        tableModel.setRowCount(0); // Clear existing data
        
        for (Student student : students) {
            Object[] row = {
                student.getStudentID(),
                student.getFullName(),
                student.getAge(),
                student.getGender(),
                student.getDepartment(),
                String.format("%.2f", student.getGPA()) 
            };
            tableModel.addRow(row);
        }
    }

    // Refresh method to update table 
    public void refresh() {
        loadTable();
        note.setText("Total Students: " + manager.getStudents().size());
    }

    //  helper method to easily get the student that the user has selected in the GUI.

    public Student getSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        
        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        return manager.searchStudent(studentId);
    }

    
 }
