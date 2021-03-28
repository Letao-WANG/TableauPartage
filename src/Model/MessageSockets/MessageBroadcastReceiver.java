package Model.MessageSockets;

import Controller.MessageController;
import Model.Parameter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
            InetAddress group = InetAddress.getByName(Parameter.messageAddressBroadcast);
            MulticastSocket s = new MulticastSocket(Parameter.messagePortBroadcast);
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
