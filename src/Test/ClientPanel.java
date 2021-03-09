package Test;

import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class ClientPanel extends JFrame implements Runnable{
    int x1,x2,y1,y2;
    DataInputStream is;
    Graphics g;

    public static void main(String args[]) {
        ClientPanel CP =new ClientPanel();
        CP.create();
        CP.ShowUI();
    }
    //产生一个Socket类用于连接服务器，并得到输入流
    public void create() {
        try {
            Socket client =new Socket("127.0.0.1",9090);
            System.out.println("create client");
            is = new DataInputStream( client.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //构造客户端界面并启动线程
    public void ShowUI() {
        JFrame jf = new JFrame();
        jf.setTitle("客户端界面");
        jf.setSize(300,300);
        jf.setDefaultCloseOperation(3);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);
        g =  jf.getGraphics();
        Thread t = new Thread(this);
        t.start();

    }
    //将is输入流终中的坐标得到，并根据坐标信息画出相应的线段。
    @Override
    public void run() {
        while (true) {
            try {
                x1 = is.readInt();
                y1 = is.readInt();
                x2 = is.readInt();
                y2 = is.readInt();
                System.out.println(x1+" "+y2+" "+x2+" "+y2);
                g.drawLine(x1,y1,x2,y2);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}