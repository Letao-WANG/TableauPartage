package View;

import Controller.MessageController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * View of Message
 */

public class MessageView extends JFrame{
    MessageController messageController;
    JTextArea textShow;
    JTextArea text;
    JScrollPane scroll;
    public MessageView(MessageController messageController) {
        this.messageController = messageController;
        textShow = new JTextArea();
        text = new JTextArea(10, 10);
        scroll = new JScrollPane();

        /*
         * add listener to text input
         */
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER) {
                    messageController.sendMessage(text.getText());
                    text.setText("");
                    scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                }
            }
        });

        scroll.setBounds(10,10,180,380);
        scroll.setBackground(Color.WHITE);
        textShow.setBounds(10,10,180,380);
        textShow.setMargin( new Insets(10,10,10,10) );
        textShow.setLineWrap(true);
        textShow.setEditable(false);
        scroll.setBorder(null);
        scroll.setViewportView(textShow);

        text.setBounds(10,400,180,80);
        text.setMargin( new Insets(10,10,10,10) );

        add(scroll);
        add(text);

        setLayout(null);
        setBounds(50,50,200,500);
        getContentPane().setBackground(new Color(217, 217, 217));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void setText(String text) {
        this.textShow.setText(text);
    }
}
