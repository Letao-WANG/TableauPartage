package View;

import Controller.TableController;
import Model.Shapes.Line;
import Model.Shapes.Oval;
import Model.Shapes.Rect;
import Model.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DrawListener implements MouseListener, ActionListener {
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
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        x2 = mouseEvent.getX();
        y2 = mouseEvent.getY();
        // draw
        if ("Line".equals(name)) {
//            g.drawLine(x1, y1, x2, y2);
            Shape line = new Line(x1, y1, x2, y2, name, color);
            tableController.addShape(line);
        }
        if ("Rect".equals(name)) {
            g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
            Shape rect = new Rect(x1, y1, x2, y2, name, color);
            tableController.addShape(rect);
        }
        if ("Oval".equals(name)) {
            g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
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
