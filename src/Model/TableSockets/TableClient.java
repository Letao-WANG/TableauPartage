package Model.TableSockets;

import Model.Shapes.Line;
import Model.Shapes.Shape;
import Model.Sockets.BroadcastReceiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TableClient extends Thread{
    @Override
    public void run() {
        new TableBroadcastReceiver().start();
        /*
         * After a Client was created, send a null shape to update
         */
        AddShape(new Line());
    }

    public void AddShape(Shape shape) {
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

    public static void main(String[] args) {
        new TableClient().start();
    }
}
