package zad1;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {
    String nick;

    public ChatWindow(String nick) throws HeadlessException {
        this.nick = nick;
        setSize(800,400);
        JLabel nickLabel = new JLabel("Zalogowano jako "+nick);
        JPanel topP = new JPanel();
        topP.add(nickLabel);
        JTextArea chatHistory = new JTextArea();
        JPanel midP = new JPanel();
        midP.add(chatHistory);
        JTextArea sendArea = new JTextArea();
        JPanel sendPanel = new JPanel();
        sendPanel.add(sendArea);
        JButton sendButton = new JButton("Wyślij");
        JPanel botP = new JPanel();
        botP.add(sendButton);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(topP);
        mainPanel.add(midP);
        mainPanel.add(sendPanel);
        mainPanel.add(botP);
        midP.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        botP.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatHistory.setPreferredSize(new Dimension(700,250));
        sendArea.setPreferredSize(new Dimension(700,100));
        sendButton.setPreferredSize(new Dimension(100,50));
        chatHistory.setEditable(false);




        getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setTitle("Chat");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
