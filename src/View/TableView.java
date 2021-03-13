package View;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {

    private Shape[] shapeArray = new Shape[200];

    public TableView(){
        JFrame frame = new JFrame("Table");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DrawListener listener = new DrawListener();
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();

        this.setBackground(Color.white);

        this.addMouseListener(listener);
        frame.add(this, BorderLayout.CENTER);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);


        // Add shape button
        String[] shapes = { "Line", "Rect", "Oval", "Brush", "Text", "Eraser", "Undo" };
        for (int i = 0; i < shapes.length; i++) {
            // create button
            JButton button = new JButton(shapes[i]);
            button.setFont(new Font("Arial", Font.BOLD, 10));
            button.setBorder(null);
            button.setPreferredSize(new Dimension(60, 25));

            panelNorth.add(button);
            button.addActionListener(listener);
        }

        //Add color button
        Color[] colorArray = { Color.red, Color.pink, Color.orange, Color.yellow, Color.green, Color.blue, Color.cyan,
                Color.black, Color.gray, Color.white };
        for (int i = 0; i < colorArray.length; i++) {
            JButton button = new JButton();
            button.setBackground(colorArray[i]);
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
        // init shape array
        listener.setSp(shapeArray);
    }

//    public void paint(Graphics g) {
//        super.paint(g);
//        for (int i = 0; i < shapeParameter.length; i++) {
//            Shape shape = shapeParameter[i];
//            if (shapeParameter[i] != null) {
//                shape.drawShape(g);
//            }
//        }
//    }

    public static void main(String[] args) {
        new TableView();
    }
}
