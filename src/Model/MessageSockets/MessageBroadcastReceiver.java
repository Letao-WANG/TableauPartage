package Model.MessageSockets;

import Controller.MessageController;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Detect, receive broadcast signal from Server and print out the updated information.
 */

public class MessageBroadcastReceiver extends Thread {
    MessageController messageController;
    public MessageBroadcastReceiver(MessageController messageController) {
        this.messageController = messageController;
    }
    @Override
    public void run() {
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            byte[] arb = new byte[1024];
            s.joinGroup(group);
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(arb, arb.length);
                s.receive(datagramPacket);
                messageController.setText(new String(arb));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
