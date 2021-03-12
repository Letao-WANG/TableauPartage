package Model.Sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

/**
 * The server, of which only one can exist at a time.
 * And it is responsible for the storage and processing of information for the client.
 */
public class Server {
    public Server() throws IOException {
        String message = "Welcome to chatroom :\n";
        DatagramSocket socket = new DatagramSocket(8800);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        while (true) {
            socket.receive(packet);
            String info = new String(data, 0, packet.getLength());
            message += info;
            new BroadcastSender(message).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
