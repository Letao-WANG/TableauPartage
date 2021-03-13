package Model.TableSockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class TableBroadcastReceiver extends Thread{
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
                System.out.println(Util.deserialize(arb));
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
