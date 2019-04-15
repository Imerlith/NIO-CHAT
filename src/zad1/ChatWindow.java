package zad1;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {
    String nick;

    public ChatWindow(String nick) throws HeadlessException {
        this.nick = nick;
    }
}
