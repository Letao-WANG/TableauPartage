package Model.TableSockets;

import Controller.TableController;
import Model.Shapes.Shape;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
            byte[] arb = new byte[4096];
            s.joinGroup(group);
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length);
                s.receive(datagramPacket);
                ArrayList<Shape> shapeList = (ArrayList<Shape>) Util.deserialize(arb);
                tableController.draw(shapeList);
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
