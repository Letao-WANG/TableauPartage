package View;

import Controller.MessageController;
import Controller.TableController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel{

    private JFrame main;
    private MessageView messageView;
    private TableView tableView;

    public MainView(TableController tableController, MessageController messageController) {

        main = new JFrame();
        setLayout(null);
        main.setTitle("Share Table");
        setBounds(50, 50, 700, 500);
        setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0,0,200,400);
        panel1.setBackground(Color.BLACK);
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBounds(200,0,500,400);
        panel2.setBackground(Color.RED);
        add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setBounds(0,400,200,100);
        panel3.setBackground(Color.ORANGE);
        add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setBounds(200,400,500,100);
        panel4.setBackground(Color.CYAN);
        add(panel4);
    }
}
