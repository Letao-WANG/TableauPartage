package Model.MessageSockets;

import Controller.MessageController;

import java.io.IOException;
import java.net.*;

/**
 * The user uses Client which can open multiple.
 * When a new user enters, information is shared with the other old users.
 */

public class MessageClient extends Thread {
    MessageController messageController;

    public MessageClient(MessageController messageController) {
        this.messageController = messageController;
    }

    @Override
    public void run() {
        new MessageBroadcastReceiver(messageController).start();

        /*
         * After a Client was created, send message to update
         * Almost equal with sendMessage()
         */
        try {
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8800;
            String message = "Welcome to new user !" + "\n";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
