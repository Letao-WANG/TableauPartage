package PaintDemo;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Pic extends JFrame{
    public  static void main(String args[]) {
        new Pic().showUI();
    }

    //构建甲方界面：300*300，设计鼠标监听器
    public void showUI() {
        this.setTitle("交互画板A");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        //设置鼠标监听器
        System.out.println("test");
        System.out.println("test2");
        this.addMouseListener(new UIListener(g));
    }
}
