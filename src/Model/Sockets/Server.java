package Model.Sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

public class Server {
    public Server() throws IOException {
        String message = "Welcome to chatroom :\n";
        /*
         * 接收客户端发送的数据
         */
        //1.创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(8800);
        //2.创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);

        while (true) {
            socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
            //4.读取数据
            String info = new String(data, 0, packet.getLength());
            message += info;
            new BroadcastSender(message).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
