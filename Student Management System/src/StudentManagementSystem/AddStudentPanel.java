package StudentManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPanel extends JPanel implements ActionListener {

    AddStudentPanel() {
        JPanel NamePanel = new JPanel();
        JLabel Name = new JLabel("Name");
        NamePanel.add(Name);
        JTextField NameField = new JTextField();
        NamePanel.add(NameField);
        NamePanel.setSize(300, 100);
        this.add(NamePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
