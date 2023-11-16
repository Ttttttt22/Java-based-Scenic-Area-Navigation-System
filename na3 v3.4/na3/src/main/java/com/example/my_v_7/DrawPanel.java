package com.example.my_v_7;//package my;
//import java.awt.*;
//
//import java.awt.event.MouseAdapter;
//
//import java.awt.event.MouseEvent;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.*;
//
//public class DrawPanel extends JPanel {
//
//    private static final long serialVersionUID = 1L;
//
//    private List<Point> points=new ArrayList<>();
//
//    public DrawPanel() {
//
//        points = new ArrayList();
//
//        setBackground(Color.WHITE);
//
//        addMouseListener(new MouseAdapter() {
//
//            @Override
//
//            public void mousePressed(MouseEvent e) {
//
//                points.add(new Point(e.getX(), e.getY()));
//                System.out.println(points);
//
//                repaint();
//
//            }
//
//        });
//
//    }
//
//    @Override
//
//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//
//        Graphics2D g2 = (Graphics2D) g;
//
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g2.setColor(Color.red);
//
//        for (Point point : points) {
//
//            g2.fillOval(point.x, point.y, 20, 20);
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//
//            public void run() {
//
//                JFrame frame = new JFrame();
//                DrawPanel draw=new DrawPanel();
//                frame.add(draw);
//                draw.setLayout(null);
//                frame.setSize(1000, 1000);
//                frame.setLocationRelativeTo(null);
//                Dimension ds=new Dimension(10,1000);
//                draw.setPreferredSize(ds);
//
//                JButton ConfirmButton =new JButton("确认");//确认按钮
//                ConfirmButton.setBounds(100, 500, 1000, 25);
//                draw.add(ConfirmButton);
//
//
//
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//
//            }
//
//        });
//
//    }
//
//}