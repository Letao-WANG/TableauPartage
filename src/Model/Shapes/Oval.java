package Model.Shapes;

import java.awt.*;

public class Oval extends Shape {
    public Oval(int x1, int y1, int x2, int y2, String name, Color color, boolean shapeFull){
        super(x1,y1,x2,y2,name,color, shapeFull);
    }

    public void drawShape(Graphics g){
        g.setColor(color);
        if(shapeFull){
        g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));}
        else{
            g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        }
    }
}