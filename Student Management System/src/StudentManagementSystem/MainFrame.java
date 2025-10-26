package StudentManagementSystem;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Student Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StudentManager manager = new StudentManager("students.txt");
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Add Student", new AddStudentPanel(manager));
        tabs.addTab("View All", new ViewStudentPanel(manager));
        tabs.addTab("Search / Update", new SearchUpdatePanel(manager));
        tabs.addTab("Delete Student", new DeleteStudentPanel(manager));

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

