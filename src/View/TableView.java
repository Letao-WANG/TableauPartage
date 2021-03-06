package View;

import Controller.TableController;
import Model.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class TableView extends JPanel {

    private DrawListener listener;
    private CopyOnWriteArrayList<Shape> shapeList;
    private ArrayList<Integer> clientShapesIndex;

    public TableView(TableController tableController) {

        this.listener = new DrawListener(tableController);
        this.shapeList = new CopyOnWriteArrayList<>();
        this.clientShapesIndex = new ArrayList<>();

        JFrame frame = new JFrame("Table");
        frame.setBounds(250, 50, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelShape = new JPanel();
//        JPanel panelDraw = new JPanel();
        JPanel panelColor = new JPanel();

        this.setBackground(Color.white);

        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        frame.add(panelShape, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);
        frame.add(panelColor, BorderLayout.SOUTH);

        // Add shape button
        String[] shapes = {"Line", "Rect", "Oval", "Full ", "Brush", "Text", "Eraser", "Free Eraser", "Undo"};
        for (String shape : shapes) {
            // create button
            JButton button = new JButton(shape);
            button.setFont(new Font("Arial", Font.BOLD, 10));
            button.setBorder(null);
            button.setPreferredSize(new Dimension(57, 25));

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
        Graphics g = this.getGraphics();
        // init graphics
        listener.setGr(g);
    }

    public void setShapeList(CopyOnWriteArrayList<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public CopyOnWriteArrayList<Shape> getShapeList() {
        return this.shapeList;
    }

    public ArrayList<Integer> getClientShapesIndex() {
        return clientShapesIndex;
    }

    public void setLastShapeIndex(int index){
        clientShapesIndex.add(index);
    }

    public void removeLastShapeIndex() {
        this.clientShapesIndex.remove(this.clientShapesIndex.size()-1);
    }

    public void paint(Graphics g) {
        super.paint(g);
        shapeList.forEach((shape) -> {
            if (shape != null) {
                shape.drawShape(g);
            }
        });
    }
}
