package View;

import Controller.TableController;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {

    private DrawListener listener;

    public TableView(TableController tableController) {

        this.listener = new DrawListener(tableController);

        JFrame frame = new JFrame("Table");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();

        this.setBackground(Color.white);

        this.addMouseListener(listener);
        frame.add(this, BorderLayout.CENTER);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);

        // Add shape button
        String[] shapes = {"Line", "Rect", "Oval", "Brush", "Text", "Eraser", "Undo"};
        for (String shape : shapes) {
            // create button
            JButton button = new JButton(shape);
            button.setFont(new Font("Arial", Font.BOLD, 10));
            button.setBorder(null);
            button.setPreferredSize(new Dimension(60, 25));

            panelNorth.add(button);
            button.addActionListener(listener);
        }

        //Add color button
        Color[] colorArray = {Color.red, Color.pink, Color.orange, Color.yellow, Color.green, Color.blue, Color.cyan,
                Color.black, Color.gray, Color.white};
        for (Color color : colorArray) {
            JButton button = new JButton();
            button.setBackground(color);
            button.setPreferredSize(new Dimension(40, 25));
            button.setBorder(null);
            panelSouth.add(button);
            button.addActionListener(listener);
        }

        frame.setVisible(true);
        // get graphics
        Graphics g = this.getGraphics();
        // init graphics
        listener.setGr(g);
    }

    public DrawListener getDrawListener() {
        return this.listener;
    }
}
