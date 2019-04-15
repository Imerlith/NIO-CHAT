package zad1;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ChatWindow extends JFrame {
    private String nick;
    private SocketChannel channel;
    private JTextArea chatHistory;
    private JTextArea sendArea;
    public ChatWindow(String nick) throws HeadlessException {
        this.nick = nick;
        setSize(800,400);
        JLabel nickLabel = new JLabel("Zalogowano jako "+nick);
        JPanel topP = new JPanel();
        topP.add(nickLabel);
         chatHistory = new JTextArea();
        JPanel midP = new JPanel();
        midP.add(chatHistory);
         sendArea = new JTextArea();
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

        sendButton.addActionListener(action->{
            if (sendArea.getText().length()>0){
                sendMessage(sendArea.getText());
            }
        });

        DefaultCaret caret =(DefaultCaret)chatHistory.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        midP.setMaximumSize(new Dimension(700,250));

        new Thread(this::startClientProcess).start();
        getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setTitle("Chat");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void startClientProcess(){

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(9999));
            channel = socketChannel;
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            String input = CommUtils.readToString(channel);
            updateChatHistory(input);
        }

    }
    private void updateChatHistory(String nMessage){
        chatHistory.append(nMessage+"\n");
        chatHistory.update(chatHistory.getGraphics());
    }
    private void sendMessage(String Message){
        CommUtils.writeFromString(nick+": "+Message,channel);
        sendArea.setText("");
    }


}
