package Model.MessageSockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * After the UDP signal from the client is detected in Server,
 * this class send the updated information to all clients in the group.
 */
public class MessageBroadcastSender extends Thread {
    private String sendMessage;

    /**
     * @param sendMessage the updated information from Server
     */
    public MessageBroadcastSender(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public void run() {
        int port = 6789;
        try {
            InetAddress inetAddress = InetAddress.getByName("228.5.6.7");
            DatagramPacket datagramPacket = new DatagramPacket(sendMessage.getBytes(), sendMessage.length(), inetAddress, port);
            MulticastSocket multicastSocket = new MulticastSocket();
            multicastSocket.send(datagramPacket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
