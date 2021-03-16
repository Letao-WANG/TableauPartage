package Model.MessageSockets;

import Controller.MessageController;

import java.io.IOException;
import java.net.*;

/**
 * The user uses Client which can open multiple.
 * When a new user enters, chat information is shared with the other old users.
 */
public class MessageClient extends Thread {
    private MessageController messageController;
    private String name = "";

    public MessageClient(MessageController messageController, String name) {
        this.messageController = messageController;
        this.name = name;
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
            String message = "Welcome to " + name + " !" + "\n";
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
            String message = name + " says : " + mess + "\n";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
