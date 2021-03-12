package View;

import Controller.MessageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageView extends JFrame implements ActionListener {
    MessageController messageController;
    JTextArea textShow;
    JTextArea text;
    JButton button;
    public MessageView(MessageController messageController) {
        this.messageController = messageController;
        textShow = new JTextArea();
        text = new JTextArea(10, 10);
        button = new JButton("Send");
        button.addActionListener(this);

        textShow.setBounds(0,0,200,400);
        text.setBounds(0,400,200,50);
        button.setBounds(100,450,100,30);

        add(textShow);
        add(text);
        add(button);

        setLayout(null);
        setBounds(0,0,200,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Send")) {
            messageController.sendMessage(text.getText());
        }
    }

    public void setText(String text) {
        this.textShow.setText(text);
    }
}
