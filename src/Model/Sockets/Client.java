package Model.Sockets;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public Client() throws IOException {
        new BroadcastReceiver().start();

        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine()) {
            String message = "user : " + scan.nextLine() + "\n";
            byte[] data = message.getBytes();
            //2.创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            //3.创建DatagramSocket对象
            DatagramSocket socket = new DatagramSocket();
            //4.向服务器端发送数据报
            socket.send(packet);
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
