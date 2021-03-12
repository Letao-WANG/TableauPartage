package PaintDemo;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UIListener implements MouseListener,Runnable {
    DataOutputStream os;
    volatile int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
    Graphics g;
    //在设置监听器的同时启动监听器对象的线程
    public UIListener(Graphics g) {
        this.g = g;
        System.out.println("create uilistener");
        Thread t = new Thread(this);
        System.out.println("create thread");
        t.start();
    }
    //记录鼠标按下的坐标
    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }
    //记录鼠标释放的坐标，且在界面上画出按下点到是释放点的线段
    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        g.drawLine(x1, y1, x2, y2);

    }

    /*
     * run（）中将本台电脑作为一个服务器端口设置为9090，在客户端连接上之后得到数据输出流
     */

    public void run() {
        try {
            //设置服务器并置于等待链接状态
            ServerSocket ss = new ServerSocket(9090);
            System.out.println("server wait");
            Socket server = ss.accept();
            System.out.println("success!");
            //连接成功后得到数据输出流
            os = new DataOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //x1,y1为起始点坐标，x2,y2为终点坐标。四个点的初始值设为0
        while (true) {
            //服务器界面画下一条线时，将四个点的信息写入到数据输出流中，之后将四个数据置0
            if(x1!=0&&x2!=0) {
                try {
                    os.writeInt(x1);
                    os.writeInt(y1);
                    os.writeInt(x2);
                    os.writeInt(y2);
                    x1 = 0;
                    y1 = 0;
                    x2 = 0;
                    y2 = 0;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
