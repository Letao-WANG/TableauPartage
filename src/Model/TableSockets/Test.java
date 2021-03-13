package Model.TableSockets;

import Model.Shapes.Line;

import java.io.IOException;

public class Test{
    public static void main(String[] args) throws IOException {
        Line line = new Line();
        byte[] data = Util.serialize(line);
    }
}
