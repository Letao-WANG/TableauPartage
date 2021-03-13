package Controller;

import Model.Shapes.Shape;
import Model.TableSockets.TableClient;
import Model.TableSockets.TableServer;
import View.TableView;

import java.util.ArrayList;

/**
 * Controller Of Table
 * Multiple TableController can exist at the same time
 */
public class TableController {
    private TableView tableView;
    private TableClient tableClient;

    public TableController() {
        new TableServer().start();
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

    public void draw(ArrayList<Shape> shapeList) {
        tableView.getDrawListener().draw(shapeList);
    }

}
