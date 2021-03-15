package View;

import Controller.TableController;
import Model.Shapes.*;
import Model.Shapes.Shape;
import Model.TableSockets.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {
    private TableController tableController;
    private int x1, y1, x2, y2;
    private String name;
    private Graphics g;
    private Color color;

    public DrawListener(TableController tableController) {
        this.tableController = tableController;
    }

    // init graphics
    public void setGr(Graphics g) {
        this.g = g;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();

        if ("Text".equals(name)) {
            Shape text = new Text(x1, y1, x2, y2, name, color);
            tableController.addShape(text);
        }

        if ("Eraser".equals(name)) {
            CopyOnWriteArrayList<Shape> shapeList = tableController.getShapeList();
            for (int i = shapeList.size() - 1; i >= 0; i--) {
                Shape shape = shapeList.get(i);

                if (shape.getName().equals("Line")) {
                    float k = ((shape.getY1() - shape.getY2()) / ((float) shape.getX1() - shape.getX2()));
                    if (Util.approximatelyEqual(y1, k * x1 + shape.getY1() - k * shape.getX1(), 8)) {
                        tableController.removeShape(i);
                        break;
                    }
                }

                if (shape.getName().equals("Rect")
                        && shape.getX1() < x1 && shape.getX2() > x1 && shape.getY1() < y1 && shape.getY2() > y1) {
                    tableController.removeShape(i);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        x2 = mouseEvent.getX();
        y2 = mouseEvent.getY();
        // draw
        if ("Line".equals(name)) {
            Shape line = new Line(x1, y1, x2, y2, name, color);
            tableController.addShape(line);
        }
        if ("Rect".equals(name)) {
            Shape rect = new Rect(x1, y1, x2, y2, name, color);
            tableController.addShape(rect);
        }
        if ("Oval".equals(name)) {
            Shape oval = new Oval(x1, y1, x2, y2, name, color);
            tableController.addShape(oval);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if ("Brush".equals(name)) {
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            g.drawLine(x1, y1, x2, y2);
            Shape line = new Line(x1, y1, x2, y2, name, color);
            tableController.addShape(line);
            x1 = x2;
            y1 = y2;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if user click color button
        if ("".equals(actionEvent.getActionCommand())) {
            JButton button = (JButton) actionEvent.getSource();
            color = button.getBackground();
            g.setColor(color);
        }
        // if user click shape button
        else {
            name = actionEvent.getActionCommand();
        }
    }
}
