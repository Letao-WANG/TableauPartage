package View;

import javax.swing.*;
import java.awt.*;

public class MessageDisplayView extends JFrame {
    public MessageDisplayView() {
        setLayout(null);
        setSize(200, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.blue);
    }

    public static void main (String [] args){
        new MessageDisplayView();
    }
}
