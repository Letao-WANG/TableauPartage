package Model.TableSockets;

import Controller.TableController;
import Model.Shapes.Line;
import Model.Shapes.Shape;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TableClient extends Thread {

    private TableController tableController;

    public TableClient(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    public void run() {
        new TableBroadcastReceiver(tableController).start();
        /*
         * After a Client was created, send a null shape to update
         */
        addShape(new Line(10, 10, 0, 0, " ", Color.WHITE));
    }

    public void addShape(Shape shape) {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8900;
            byte[] data = Util.serialize(shape);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
