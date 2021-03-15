package Model.Shapes;

import java.awt.*;

public class Text extends Shape{
    public Text(int x1, int y1, int x2, int y2, String name, Color color){
        super(x1,y1,x2,y2,name,color);
    }

    public void drawShape(Graphics g){
        g.setColor(color);
        g.drawString(name, x1, y1);

    }
}
