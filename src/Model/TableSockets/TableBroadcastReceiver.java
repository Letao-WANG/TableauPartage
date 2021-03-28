package Model.TableSockets;

import Controller.TableController;
import Model.Parameter;
import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Detect, receive broadcast signal from table Server and draw these shapes.
 */
public class TableBroadcastReceiver extends Thread {
    private TableController tableController;

    public TableBroadcastReceiver(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    public void run() {
        try {
            InetAddress group = InetAddress.getByName(Parameter.tableAddressUDP);
            MulticastSocket s = new MulticastSocket(Parameter.tablePortBroadcast);
            byte[] arb = new byte[409600];
            s.joinGroup(group);
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length);
                s.receive(datagramPacket);
                CopyOnWriteArrayList<Shape> shapeList = (CopyOnWriteArrayList<Shape>) Util.deserialize(arb);
                tableController.setShapeList(shapeList);
                tableController.repaint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
