package Model.Sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.SocketException;

/**
 * The server, of which only one can exist at a time.
 * And it is responsible for the storage and processing of information for the client.
 */
public class Server extends Thread {
    public void run() {
        /*
         * Which is shown in the text label GUI
         */
        String message = "Welcome to chatroom :\n";
        try {
            DatagramSocket socket = new DatagramSocket(8800);
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            /*
             * Send updated messages to all Client each time a new Client enters or a Client sends a message
             */
            while (true) {
                socket.receive(packet);
                String info = new String(data, 0, packet.getLength());
                message += info;
                new BroadcastSender(message).start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
