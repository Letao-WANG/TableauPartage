package Controller;

import Model.Shapes.Shape;
import Model.TableSockets.TableClient;
import View.TableView;

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

    public void removeShape(int index) {
        tableClient.removeShape(index);
    }

    public void setShapeList(CopyOnWriteArrayList<Shape> shapeList) {
        tableView.setShapeList(shapeList);
    }

    public ArrayList<Integer> getClientShapesIndex() {
        return tableView.getClientShapesIndex();
    }

    public void setLastShapeIndex(int index){
        tableView.setLastShapeIndex(index);
    }

    public void removeLastShapeIndex(int index) {
        tableView.removeLastShapeIndex(index);
    }

    public CopyOnWriteArrayList<Shape> getShapeList() {
        return tableView.getShapeList();
    }

    public void repaint() {
        tableView.repaint();
    }

}
