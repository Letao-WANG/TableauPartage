package Model.TableSockets;

import Controller.TableController;
import Model.Parameter;
import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * The user uses table Client which can open multiple.
 * When a new user enters, shapes are shared with the other old users.
 */
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
        addShape(null);
    }

    public void addShape(Shape shape) {
        try {
            InetAddress address = InetAddress.getByName(tableController.address);
            int port = Parameter.tablePort;
            byte[] data = Util.serialize(shape);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeShape(int index){
        try {
            InetAddress address = InetAddress.getByName(tableController.address);
            int port = Parameter.tablePort;
            byte[] data = Util.serialize(index);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
