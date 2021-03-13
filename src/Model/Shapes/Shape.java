package Model.Shapes;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    public int x1, y1, x2, y2;
    public String name;
    public Color color;

    public Shape() {
    }

    public Shape(int x1, int y1, int x2, int y2, String name, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.name = name;
        this.color = color;
    }

    public void drawShape(Graphics g) {

    }

    public int getX1(){
        return this.x1;
    }
}