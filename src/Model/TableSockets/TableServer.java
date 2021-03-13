package Model.TableSockets;

import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * The table server, of which only one can exist at a time.
 * And it is responsible for the storage and processing of shape list for the client.
 */
public class TableServer extends Thread {
    public void run() {
        ArrayList<Shape> shapeList = new ArrayList<Shape>();

        try {
            DatagramSocket socket = new DatagramSocket(8900);
            byte[] data = new byte[4096];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            /*
             * Send updated shape array to all Client each time a new Client enters or a Client draw a new shape
             */
            while (true) {
                socket.receive(packet);
                Shape shape = (Shape) Util.deserialize(data);
                shapeList.add(shape);
                new TableBroadcastSender(shapeList).start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TableServer().start();
    }

}
