package Model.TableSockets;

import Controller.TableController;
import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
            InetAddress group = InetAddress.getByName("228.5.6.8");
            MulticastSocket s = new MulticastSocket(6790);
            byte[] arb = new byte[40960];
            s.joinGroup(group);
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length);
                s.receive(datagramPacket);
                CopyOnWriteArrayList<Shape> shapeList = (CopyOnWriteArrayList<Shape>) Util.deserialize(arb);
                tableController.setShapeList(shapeList);
                tableController.repaint();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
