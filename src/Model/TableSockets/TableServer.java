package Model.TableSockets;

import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The table server, of which only one can exist at a time.
 * And it is responsible for the storage and processing of shape list for the client.
 */
public class TableServer extends Thread {
    public void run() {
        CopyOnWriteArrayList<Shape> shapeList = new CopyOnWriteArrayList<>();

        try {
            DatagramSocket socket = new DatagramSocket(8900);
            byte[] data = new byte[40960];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            /*
             * Send updated shape array to all Client each time a new Client enters or a Client draw a new shape
             */
            while (true) {
                socket.receive(packet);
                Object object = Util.deserialize(data);
                if (object instanceof Shape) {
                    shapeList.add((Shape) object);
                } else if (object instanceof Integer) {
                    int index = (int) object;
                    shapeList.remove(index);
                }
                new TableBroadcastSender(shapeList).start();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TableServer().start();
    }

}
