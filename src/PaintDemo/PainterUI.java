package PaintDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainterUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private Shape[] shapeParameter = new Shape[20000];

    public void initUI() {
        // 新建窗体并命名
        JFrame jf = new JFrame("画板");
        // 设置窗体大小
        jf.setSize(1100, 700);
        // 窗体设置居中
        jf.setLocationRelativeTo(null);
        // 设置窗体关闭
        jf.setDefaultCloseOperation(3);
        // 设置窗体边界布局
        jf.setLayout(new BorderLayout());

        // 添加3个JPanel容器
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        // 将JPanel布局到窗体中
        jf.add(this, BorderLayout.CENTER);
        jf.add(jp2, BorderLayout.WEST);
        jf.add(jp3, BorderLayout.EAST);

        // 设置jp1
        this.setPreferredSize(new Dimension(900, 700));
        this.setBackground(Color.white);

        // 创建事件监听器对象
        DrawListener dl = new DrawListener();
        // 给画布添加监听器
        this.addMouseListener(dl);
        this.addMouseMotionListener(dl);

        // 设置jp2
        jp2.setPreferredSize(new Dimension(100, 700));
        jp2.setBackground(Color.LIGHT_GRAY);
        // 设置jp3
        jp3.setPreferredSize(new Dimension(100, 700));
        jp3.setBackground(Color.LIGHT_GRAY);

        // 添加图形按钮
        String[] shapeArray = { "直线", "矩形", "椭圆", "多边形", "画笔", "橡皮檫", "清屏" };
        for (int i = 0; i < shapeArray.length; i++) {
            // 创建图形按钮
            JButton jbu1 = new JButton(shapeArray[i]);
            // 设置按钮大小
            jbu1.setPreferredSize(new Dimension(100, 40));
            // 将按钮添加到jp2容器中
            jp2.add(jbu1);
            // 给按钮注册监听器
            jbu1.addActionListener(dl);
        }

        // 设置颜色按钮
        Color[] colorArray = { Color.red, Color.pink, Color.orange, Color.yellow, Color.green, Color.blue, Color.cyan,
                Color.black, Color.gray, Color.white };
        for (int i = 0; i < colorArray.length; i++) {
            JButton jbu2 = new JButton();
            jbu2.setBackground(colorArray[i]);
            jbu2.setPreferredSize(new Dimension(100, 40));
            jp3.add(jbu2);
            jbu2.addActionListener(dl);
        }

        // 设置窗体可见
        jf.setVisible(true);
        // 获取画笔
        Graphics g = this.getGraphics();
        // 将画笔传递过去
        dl.setGr(g);
        // 将图形数组传递过去
        dl.setSp(shapeParameter);
    }

    // 重写父类方法
    public void paint(Graphics g) {
        super.paint(g);
        //遍历图形数组，重绘图形
        for (int i = 0; i < shapeParameter.length; i++) {
            Shape shape = shapeParameter[i];
            if (shapeParameter[i] != null) {
                shape.drawShape(g);
            }
        }
    }
    //主函数
    public static void main(String[] args) {
        PainterUI pui = new PainterUI();
        pui.initUI();
    }
}
