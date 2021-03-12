package Model.Sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class BroadcastReceiver extends Thread {
    @Override
    public void run() {
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            byte[] arb = new byte[1024];
            s.joinGroup(group);//加入该组
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length);
                s.receive(datagramPacket);
                System.out.println(new String(arb));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
