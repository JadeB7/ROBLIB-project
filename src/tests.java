import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tests extends JFrame {
    public tests() {
        setTitle("Login");
        setSize(960, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JTextField usernameField = new JTextField(20);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton connectButton = new JButton("Connect");
        connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your login logic here
            }
        });

        JButton createAccountButton = new JButton("New Account");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your "Create Account" logic here
            }
        });

        panel.add(Box.createVerticalStrut(30)); // Spacing between logo and username field
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(10)); // Spacing between username and password field
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(20)); // Spacing between password field and buttons
        panel.add(connectButton);
        panel.add(Box.createVerticalStrut(10)); // Spacing between buttons
        panel.add(createAccountButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            tests frame = new tests();
            frame.setVisible(true);
        });
    }
}
