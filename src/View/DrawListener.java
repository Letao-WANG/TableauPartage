package View;

import Controller.TableController;
import Model.Shapes.*;
import Model.Shapes.Shape;
import Model.TableSockets.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class of Listener
 * Defines the command to be executed after the user operate
 */
public class DrawListener implements MouseListener, MouseMotionListener, ActionListener {
    private TableController tableController;
    private int x1, y1, x2, y2;
    private String name;
    private Graphics g;
    private Color color;
    private boolean shapeFull = true;

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
            String t = JOptionPane.showInputDialog("Please enter the text you want to display :");
            Shape text = new Text(x1, y1, x2, y2, t, color);
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

                if (shape.getName().equals("Oval")
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
            tableController.setLastShapeIndex(tableController.getShapeList().size());
            tableController.addShape(line);
        }
        if ("Rect".equals(name)) {
            Shape rect = new Rect(x1, y1, x2, y2, name, color, shapeFull);
            tableController.setLastShapeIndex(tableController.getShapeList().size());
            tableController.addShape(rect);
        }
        if ("Oval".equals(name)) {
            Shape oval = new Oval(x1, y1, x2, y2, name, color, shapeFull);
            tableController.setLastShapeIndex(tableController.getShapeList().size());
            tableController.addShape(oval);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * When the user drags on the board
     * @param mouseEvent event
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if ("Brush".equals(name)) {
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            Shape line = new Line(x1, y1, x2, y2, name, color);
            tableController.addShape(line);
            x1 = x2;
            y1 = y2;
        }

        if ("Free Eraser".equals(name)) {
            // set width of line
            ((Graphics2D) g).setStroke(new BasicStroke(20));
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            Shape line = new Line(x1, y1, x2, y2, name, Color.white);
            tableController.addShape(line);
            x1 = x2;
            y1 = y2;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    /**
     * When the user clicks the button
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if user click color button
        if ("".equals(actionEvent.getActionCommand())) {
            JButton button = (JButton) actionEvent.getSource();
            color = button.getBackground();
            g.setColor(color);
        }
        // if user click undo button
        else if ("Undo".equals(actionEvent.getActionCommand())) {
            int length = tableController.getClientShapesIndex().size();
            if(length == 0){
                JOptionPane.showMessageDialog(null, "Cannot continue to Undo !");
            }
            else {
                tableController.removeShape(tableController.getClientShapesIndex().get(length - 1));
                tableController.removeLastShapeIndex();
            }
        }
        else if ("Full ".equals(actionEvent.getActionCommand())) {
            JButton button = (JButton) actionEvent.getSource();
          button.setText("Empty");
            this.shapeFull = false;
        }
        else if ("Empty".equals(actionEvent.getActionCommand())) {
            JButton button = (JButton) actionEvent.getSource();
            button.setText("Full ");
            this.shapeFull = true;
        }
        // if user click shape button
        else {
            name = actionEvent.getActionCommand();
        }
    }
}
