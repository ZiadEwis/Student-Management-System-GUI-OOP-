package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {
        setTitle("Login - Student Management");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Color bg = new Color(245,245,247);
        getContentPane().setBackground(bg);

        JPanel panel = new JPanel(new GridLayout(4,1,6,6));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setBackground(bg);

        userField = new JTextField();
        passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0,153,204));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        add(panel, BorderLayout.CENTER);
        add(loginBtn, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();
            if (user.equals("admin") && pass.equals("admin")) {
                new MainFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

