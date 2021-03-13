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

        JPanel panelShape = new JPanel();
        JPanel panelDraw = new JPanel();
        JPanel panelColor = new JPanel();

        panelDraw.setBackground(Color.white);

        panelDraw.addMouseListener(listener);
        frame.add(panelShape, BorderLayout.NORTH);
        frame.add(panelDraw, BorderLayout.CENTER);
        frame.add(panelColor, BorderLayout.SOUTH);

        // Add shape button
        String[] shapes = {"Line", "Rect", "Oval", "Brush", "Text", "Eraser", "Undo"};
        for (String shape : shapes) {
            // create button
            JButton button = new JButton(shape);
            button.setFont(new Font("Arial", Font.BOLD, 10));
            button.setBorder(null);
            button.setPreferredSize(new Dimension(60, 25));

            panelShape.add(button);
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
            panelColor.add(button);
            button.addActionListener(listener);
        }

        frame.setVisible(true);
        // get graphics
        Graphics g = panelDraw.getGraphics();
        // init graphics
        listener.setGr(g);
    }

    public DrawListener getDrawListener() {
        return this.listener;
    }
}
