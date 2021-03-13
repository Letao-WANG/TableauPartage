package Model.TableSockets;

import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * After the UDP signal from the table client is detected in table Server,
 * this class send the updated shape list to all clients in the group.
 */
public class TableBroadcastSender extends Thread {
    private ArrayList<Shape> shapeList;

    /**
     * @param shapeList the updated list of Shape from Server
     */
    public TableBroadcastSender(ArrayList<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        int port = 6790;
        try {
            InetAddress inetAddress = InetAddress.getByName("228.5.6.8");
            byte[] data = Util.serialize(shapeList);
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);
            MulticastSocket multicastSocket = new MulticastSocket();
            multicastSocket.send(datagramPacket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
