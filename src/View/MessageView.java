package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageView extends JFrame implements ActionListener {
    JLabel label;
    JTextArea text;
    JButton button;
    public MessageView() {
        label = new JLabel();
        text = new JTextArea(10, 10);
        button = new JButton("Send");
        button.addActionListener(this);

        label.setBounds(0,0,200,400);
        text.setBounds(0,400,200,50);
        button.setBounds(100,450,100,30);

        add(label);
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
            label.setText(text.getText());
        }
    }

    public static void main(String[] args) {
        new MessageView();
    }
}
