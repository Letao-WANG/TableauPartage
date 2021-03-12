package Model.Sockets;

import Controller.MessageController;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * The user uses Client which can open multiple.
 * When a new user enters, information is shared with the other old users.
 */

public class Client extends Thread {
    MessageController messageController;

    public Client(MessageController messageController) {
        this.messageController = messageController;
    }

    @Override
    public void run() {
        new BroadcastReceiver(messageController).start();
        sendMessage("Welcome to new user !");
    }

    public void sendMessage(String mess) {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8800;
            String message = "user says : " + mess + "\n";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
