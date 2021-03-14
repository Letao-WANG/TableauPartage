package Controller;

import Model.Shapes.Line;
import Model.Shapes.Shape;
import Model.TableSockets.TableClient;
import Model.TableSockets.TableServer;
import View.TableView;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Controller Of Table
 * Multiple TableController can exist at the same time
 */
public class TableController {
    private TableView tableView;
    private TableClient tableClient;

    public TableController() {
        try {
            new TableServer().start();
        } catch (Exception e) {
            System.out.println();
        }
        this.tableView = new TableView(this);
        this.tableClient = new TableClient(this);
        tableClient.start();
    }

    public static void main(String[] args) {
        new TableController();
    }

    public void addShape(Shape shape) {
        tableClient.addShape(shape);
    }

    public void setShapeList(CopyOnWriteArrayList<Shape> shapeList) {
        tableView.setShapeList(shapeList);
    }

    public void repaint() {
        tableView.repaint();
    }

}
