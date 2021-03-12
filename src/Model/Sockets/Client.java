package Model.Sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * The user uses Client which can open multiple.
 * When a new user enters, information is shared with the other old users.
 */

public class Client {
    public Client() throws IOException {
        new BroadcastReceiver().start();

        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {
            String message = "user : " + scan.nextLine() + "\n";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
