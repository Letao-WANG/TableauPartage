package Model.Sockets;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * The user uses Client which can open multiple.
 * When a new user enters, information is shared with the other old users.
 */

public class Client extends Thread {
    public void run() {
        new BroadcastReceiver().start();
        try {
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
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String mess) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        String message = "user : " + mess + "\n";
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
