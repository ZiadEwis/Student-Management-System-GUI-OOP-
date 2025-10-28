package StudentManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStudentPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JLabel statusLabel;
    private StudentManager manager;

    public DeleteStudentPanel(StudentManager manager) {
        this.manager = manager;
        
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 247));

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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Select Student to Delete"));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel 
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(getBackground());
        
        // Delete Button
        deleteButton = new JButton("Delete Selected Student");
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Status Label
        statusLabel = new JLabel("Select a student from the table to delete");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        bottomPanel.add(deleteButton, BorderLayout.NORTH);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load initial data
        loadTable();

        // Delete Button Action 
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedStudent();
            }
        });
    }
    
    private void refreshAllTabs() {
        Component parent = this.getParent();
        while (parent != null && !(parent instanceof JTabbedPane)) {
            parent = parent.getParent();
        }
        if (parent instanceof JTabbedPane tabs) {
            for (int i = 0; i < tabs.getTabCount(); i++) {
                Component c = tabs.getComponentAt(i);
                if (c instanceof ViewStudentPanel) ((ViewStudentPanel) c).refresh();
                if (c instanceof DeleteStudentPanel) ((DeleteStudentPanel) c).refresh();
                if (c instanceof SearchUpdatePanel) ((SearchUpdatePanel) c).refresh();
            }
        }
    }

    private void deleteSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student from the table to delete.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        String studentName = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete student:\n" +
            "ID: " + studentId + "\n" +
            "Name: " + studentName + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            
        if (confirmation == JOptionPane.YES_OPTION) {
            manager.deleteStudent(studentId);
            manager.saveFiles(); // Save changes to file
            loadTable(); // Refresh the table
            statusLabel.setText("Student deleted successfully!");
            
            // Clear status message after 3 seconds
            Timer timer = new Timer(3000, e -> {
                statusLabel.setText("Select a student from the table to delete");
            });
            timer.setRepeats(false);
            timer.start();
            refreshAllTabs();
        }
    }

    private void loadTable() {
        tableModel.setRowCount(0); // Clear existing data
        
        for (Student student : manager.getStudents()) {
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
        
        // Update status label with current count
        statusLabel.setText("Total Students: " + manager.getStudents().size() + " - Select one to delete");
    }

    public void refresh() {
        loadTable();
    }
}



