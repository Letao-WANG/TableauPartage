package Model.MessageSockets;

import Model.Parameter;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.net.SocketException;

/**
 * The message server, of which only one can exist at a time.
 * And it is responsible for the storage and processing of chat information for the client.
 */
public class MessageServer extends Thread {
    public void run() {
        /*
         * Which is shown in the text label GUI
         */
        String message = "Welcome to chatroom :\n";
        try {
            DatagramSocket socket = new DatagramSocket(Parameter.messagePort);
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            /*
             * Send updated messages to all Client each time a new Client enters or a Client sends a message
             */
            while (true) {
                socket.receive(packet);
                String info = new String(data, 0, packet.getLength());
                message += info;
                new MessageBroadcastSender(message).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessageServer().start();
    }
}
