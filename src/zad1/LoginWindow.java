package zad1;

import javax.swing.*;
import java.awt.*;


public class LoginWindow extends JFrame {
    public LoginWindow() {
        setSize(400,200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login ");
        JLabel topLabel = new JLabel("Proszę podać swój nick");
        JTextField loginTxtField = new JTextField();
        JButton button = new JButton("Potwierdź");
        button.addActionListener(action->{
            SwingUtilities.invokeLater(()->new ChatWindow(loginTxtField.getText()));
            dispose();
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel topP = new JPanel();
        JPanel midP = new JPanel();
        JPanel botP = new JPanel();
        topP.setAlignmentX(Component.LEFT_ALIGNMENT);
        midP.setAlignmentX(Component.CENTER_ALIGNMENT);
        botP.setAlignmentX(Component.CENTER_ALIGNMENT);
        topP.add(topLabel);
        midP.add(loginTxtField);
        botP.add(button);
        panel.add(topP);
        panel.add(midP);
        panel.add(botP);
        topLabel.setPreferredSize(new Dimension(300,50));
        loginTxtField.setPreferredSize(new Dimension(250,50));
        button.setPreferredSize(new Dimension(100,30));
        loginTxtField.setHorizontalAlignment(SwingConstants.CENTER);
        loginTxtField.setFont(new Font("Century", Font.PLAIN,15));
        topLabel.setFont(new Font("Century",Font.ITALIC,20));
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
