package Model.TableSockets;

import Model.Parameter;
import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * After the UDP signal from the table client is detected in table Server,
 * this class send the updated shape list to all clients in the group.
 */
public class TableBroadcastSender extends Thread {
    private CopyOnWriteArrayList<Shape> shapeList;

    /**
     * @param shapeList the updated list of Shape from Server
     */
    public TableBroadcastSender(CopyOnWriteArrayList<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        int port = Parameter.tablePortBroadcast;
        try {
            InetAddress inetAddress = InetAddress.getByName(Parameter.tableAddressUDP);
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
