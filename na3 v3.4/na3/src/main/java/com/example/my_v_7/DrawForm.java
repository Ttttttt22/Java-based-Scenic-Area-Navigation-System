package com.example.my_v_7;


import com.example.dao.entity.Spot;
import com.example.dao.mapper.SpotMapper;
import com.example.t_v_2.ReadTxt;
import com.example.t_v_2.TriTuple;
import com.example.t_v_2.adjMatrix;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DrawForm<hashMap> extends JFrame {

    //测试mapper
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static SpotMapper mapper = null;  //定义映射器接口类型的对象

    static {
        sqlSession = MyBatisUtil.getSqlSession();
        mapper = sqlSession.getMapper(SpotMapper.class);
    }
    //

    private static final long serialVersionUID = 1L;
    //static int DrawFormCount=1;  //此静态变量用做“新建”菜单创建的绘图窗口数
    private int width = 1530;  //定义窗体的宽度
    private int height = 720;  //定义窗体的高度
    private String Title;    //定义窗体名字Title，用作保存时使用
    JMenuBar JMenubar;  //定义菜单条
    JMenu JMenuChangeUser;     //定义菜单“切换用户”
    Graphics g;


    JPanel JPanelTop;   //定义JPanelTop面板，放ID，Name相关JButton和JTextField
    JButton BtnVerify;
    JLabel LbFrom;  //定义BtnFrom按钮
    private JTextField TfdFrom;  //定义TfdFrom文本框
    JLabel LbTo;  //定义BtnTo按钮
    private JTextField TfdTo;  //定义TfdTo文本框

    JLabel Lbdel;
    private JTextField TfdelPoint;//删除点按键
    JButton Btndel;

    JTextField testId;
    JTextField testName;
    //--------
    JLabel Lblength;
    private JTextField Tflength;
    JLabel Lbstart;
    private JTextField TfdStart;
    JLabel Lbend;  //定义BtnTo按钮
    private JTextField TfdEnd;
    JButton calculate;//
    JButton BtnShortest;
    JButton Search;
    JButton Update;
    JLabel distance;//
    //--------
    JButton BtnDelete;
    JTextArea Message;
    JTextArea Messagedes;
    JPanel JPanelWorkPlace;  //定义JPanelWorkPlace,用来放JPanelWorkOfLeft和JPanelWorkOfDraw还有JPanelWorkRight
    JPanel JPanelWorkOfLeft;  //定义JPanelWorkOfLeft用来放 选择绘图的哪个图案的按钮
    JPanel JPanelWorkRight;//定义JPanelWorkRight放
    JToggleButton BtnDrawOval;  //定义画椭圆的BtnDrawOval按钮
    JPanel JPanelWorkOfDraw;  //定义JPanelWorkOfDraw，用做画图面板
    JScrollPane ma;

    //--------
    HashMap<String, Point> pp = new HashMap<String, Point>();
    HashMap<String, TriTuple> ee = new HashMap<String, TriTuple>();
    boolean flag = false;
    adjMatrix adj = new adjMatrix(0, 0);
    //JMenu JMenufileChooser;
    JFileChooser fileChooser;
    ReadTxt rt = new ReadTxt();
    //-----

    int cnt = 1;


    public DrawForm(String title) {  //构造函数初始化
       // DrawForm Draw=new DrawForm("tsb");
        //setResizable(false);
        //setUndecorated(true);
         Title = title;
        setTitle(title);     //设置标题
        InitComponent();          //初始化窗体组件
        AddComponent();           //添加组件到窗口
        AddListenerToComponent();  //给组件添加监听器
        setLayout(null);  //设置窗体的布局为空（null）布局
        setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2), (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2, width, height);   //根据当前屏幕大小设置窗体的大小和位置
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        adj = rt.readTxtAdjMatrix("adjdata.txt");
        pp = rt.readHash("ppdata.txt");
        cnt = rt.readint("Zcntdata.txt");
        adj.printGraph();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            public void run() {
                Graphics g=JPanelWorkOfDraw.getGraphics();
                if(g!=null){
                    //g.setColor(Color.orange);
                    adj.printPanel(JPanelWorkOfDraw, pp);
                }

            }
        }, 1000);

        }

    private void InitComponent() {   //初始化窗体组件函数
        JMenubar = new JMenuBar();
        JMenuChangeUser = new JMenu("切换用户");

        //JMenufileChooser = new JMenu("打开目录");


        JPanelTop = new JPanel();
        JPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1)); //设置JPanelTop的布局为FlowLayout.CENTER
        JPanelTop.setBorder(new LineBorder(new Color(0, 0, 0)));  //设置JPanelTop的边框
        JPanelTop.setBounds(0, 0, 1500, 40);  //设置JPanelTop的位置，上层容器布局需要设置为null布局


        Btndel = new JButton("Pdel");
        Btndel.setBackground(new Color(225, 225, 225));
        Btndel.setPreferredSize(new Dimension(67, 28));

        BtnVerify = new JButton("insert");
        BtnVerify.setBackground(new Color(225, 225, 225));
        BtnVerify.setPreferredSize(new Dimension(67, 28));

        calculate = new JButton("calculate");
        calculate.setBackground(new Color(225, 225, 225));
        calculate.setPreferredSize(new Dimension(100, 28));

        BtnDelete = new JButton("Delete");
        BtnDelete.setBackground(new Color(225, 225, 225));
        BtnDelete.setPreferredSize(new Dimension(100, 28));

        BtnShortest = new JButton("Shortest");
        BtnShortest.setBackground(new Color(225, 225, 225));
        BtnShortest.setPreferredSize(new Dimension(100, 28));

        Lbdel = new JLabel("delPoint");
        Lbdel.setBackground(new Color(225, 225, 225));
        Lbdel.setPreferredSize(new Dimension(67, 28));

        LbFrom = new JLabel("From");
        LbFrom.setBackground(new Color(225, 225, 225));
        LbFrom.setPreferredSize(new Dimension(67, 28));  //设置BtnID按钮的大小，在FlowLayout布局的组件需要使用setPreferredSize(new Dimension())方法
        LbFrom.setHorizontalAlignment(JTextField.RIGHT);
        LbTo = new JLabel("To");
        LbTo.setBackground(new Color(225, 225, 225)); //设置BtnName的背景颜色
        LbTo.setPreferredSize(new Dimension(67, 28));  //设置BtnName按钮的大小，在FlowLayout布局的组件需要使用setPreferredSize(new Dimension())方法
        LbTo.setHorizontalAlignment(JTextField.RIGHT);
        Lblength = new JLabel("length");
        Lblength.setBackground(new Color(225, 225, 225));
        Lblength.setPreferredSize(new Dimension(67, 28));
        Lblength.setHorizontalAlignment(JTextField.RIGHT);
        Lbstart = new JLabel("start");
        Lbstart.setBackground(new Color(225, 225, 225));
        Lbstart.setPreferredSize(new Dimension(67, 28));
        Lbstart.setHorizontalAlignment(JTextField.RIGHT);
        Lbend = new JLabel("To");
        Lbend.setBackground(new Color(225, 225, 225));
        Lbend.setPreferredSize(new Dimension(67, 28));
        Lbend.setHorizontalAlignment(JTextField.RIGHT);


        TfdelPoint = new JTextField();
        TfdelPoint.setPreferredSize(new Dimension(80, 28));
        TfdelPoint.setHorizontalAlignment(JTextField.CENTER);

        TfdFrom = new JTextField();
        TfdFrom.setPreferredSize(new Dimension(80, 28));
        TfdFrom.setHorizontalAlignment(JTextField.CENTER);
        TfdTo = new JTextField();
        TfdTo.setPreferredSize(new Dimension(80, 28));
        TfdTo.setHorizontalAlignment(JTextField.CENTER);
        Tflength = new JTextField();
        Tflength.setPreferredSize(new Dimension(80, 28));
        Tflength.setHorizontalAlignment(JTextField.CENTER);
        TfdStart = new JTextField();
        TfdStart.setPreferredSize(new Dimension(80, 28));
        TfdStart.setHorizontalAlignment(JTextField.CENTER);
        TfdEnd = new JTextField();
        TfdEnd.setPreferredSize(new Dimension(80, 28));
        TfdEnd.setHorizontalAlignment(JTextField.CENTER);

        testId = new JTextField("ID");
        testId.setPreferredSize(new Dimension(130, 65));
        testId.setHorizontalAlignment(JButton.LEFT);
        testId.setBackground(Color.white);

        testName = new JTextField("Name");
        testName.setPreferredSize(new Dimension(130, 65));
        testName.setHorizontalAlignment(JButton.LEFT);
        testName.setBackground(Color.white);


        JPanelWorkPlace = new JPanel();
        JPanelWorkPlace.setLayout(null);  //设置JPanelWorkPlace的布局为空（null）布局
        JPanelWorkPlace.setBorder(new LineBorder(new Color(0, 0, 0)));  //设置JPanelWorkPlace的边框
        JPanelWorkPlace.setBounds(0, 40, 1500, 617);  //设置JPanelWorkPlace的位置和大小1280

        JPanelWorkOfLeft = new JPanel();
        JPanelWorkOfLeft.setBorder(new LineBorder(new Color(0, 0, 0)));  //设置JPanelWorkOfLeft的边框
        JPanelWorkOfLeft.setBounds(10, 10, 150, 597);  //设置JPanelWorkOfLeft的位置
        JPanelWorkOfLeft.setBackground(Color.white);

        JPanelWorkRight = new JPanel();
        JPanelWorkRight.setBorder(new LineBorder(new Color(0, 0, 0)));
        JPanelWorkRight.setBounds(1280, 10, 200, 597);
        JPanelWorkRight.setPreferredSize(new Dimension(210,100000));
        JPanelWorkRight.setBackground(Color.white);
         ma=new JScrollPane(JPanelWorkRight);
         ma.setBounds(1280,10,210,590);
         ma.setHorizontalScrollBarPolicy(ma.HORIZONTAL_SCROLLBAR_NEVER);

        BtnDrawOval = new JToggleButton();
        BtnDrawOval.setSelectedIcon(new ImageIcon("my/open.png"));
        BtnDrawOval.setIcon(new ImageIcon("my/close.png"));
        BtnDrawOval.setPreferredSize(new Dimension(130, 65));
        BtnDrawOval.setHorizontalAlignment(JButton.LEFT);
        BtnDrawOval.setBackground(Color.white);

        Message = new JTextArea("路径");
        Message.setPreferredSize(new Dimension(190, 100000));
        Message.setBackground(Color.white);
        Message.setLineWrap(true);
        Message.setWrapStyleWord(true);

        Messagedes = new JTextArea("spot Information");
        Messagedes.setPreferredSize(new Dimension(130, 200));
        Messagedes.setBackground(Color.white);
        Messagedes.setBounds(10, 10, 10, 10);
        Messagedes.setLineWrap(true);
        Messagedes.setWrapStyleWord(true);


        JPanelWorkOfDraw = new JPanel();
        JPanelWorkOfDraw.setLayout(null);  //设置JPanelWorkOfDraw为空（null）布局
        JPanelWorkOfDraw.setBorder(new LineBorder(new Color(0, 0, 0)));  //设置JPanelWorkOfDraw的边框
        JPanelWorkOfDraw.setBounds(180, 10, 1075, 597);  //设置JPanelWorkOfDraw的位置
        JPanelWorkOfDraw.setBackground(Color.white);  //设置JPanelWorkOfDraw的背景颜色

        Search = new JButton("Search");
        Search.setPreferredSize(new Dimension(130, 65));
        Search.setHorizontalAlignment(JButton.LEFT);
        Search.setBackground(Color.white);

        Update = new JButton("Update");
        Update.setPreferredSize(new Dimension(130, 65));
        Update.setHorizontalAlignment(JButton.LEFT);
        Update.setBackground(Color.white);



    }

    private void AddComponent() {  //添加组件方法
        JMenubar.add(JMenuChangeUser);   //把菜单“切换用户”加到菜单条
        //JMenubar.add(JMenufileChooser);
        setJMenuBar(JMenubar);    //设置窗体菜单条
        JPanelTop.add(Lbdel);
        JPanelTop.add(TfdelPoint);
        JPanelTop.add(Btndel);


        JPanelTop.add(LbFrom);     //给JPanelTop添加关于ID、Name的组件
        JPanelTop.add(TfdFrom);   //
        JPanelTop.add(LbTo);   //
        JPanelTop.add(TfdTo);  //
        JPanelTop.add(Lblength);
        JPanelTop.add(Tflength);


        JPanelTop.add(BtnVerify);
        JPanelTop.add(BtnDelete);


        JPanelTop.add(Lbstart);
        JPanelTop.add(TfdStart);
        JPanelTop.add(Lbend);
        JPanelTop.add(TfdEnd);
        JPanelTop.add(calculate);
        JPanelTop.add(BtnShortest);

        JPanelWorkOfLeft.add(BtnDrawOval);
        JPanelWorkOfLeft.add(Search);
        JPanelWorkOfLeft.add(Update);
        JPanelWorkOfLeft.add(testId);
        JPanelWorkOfLeft.add(testName);
        JPanelWorkOfLeft.add(Messagedes);
        JPanelWorkRight.add(Message);
        JPanelWorkPlace.add(JPanelWorkOfLeft);  //把左侧功能选择和画图区域组合起来加到JPanelWorkPlace容器中
        JPanelWorkPlace.add(JPanelWorkOfDraw);
        JPanelWorkPlace.add(ma);
        add(JPanelTop);      //window容器添加JPanelTop和JPanelWorkPlace两个容器构成整个窗体
        add(JPanelWorkPlace);




    }


    private void AddListenerToComponent() {  //定义给组件添加监听器的方法

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowOpened");
                        }

                    }
                }, 1000);

            }
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowClosing");
                        }

                    }
                }, 1000);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowClosed");
                        }

                    }
                }, 1000);
            }

            @Override
            public void windowIconified(WindowEvent e) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowIconified");
                        }

                    }
                }, 1000);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowDeiconified");
                        }

                    }
                }, 1000);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowActivated");
                        }

                    }
                }, 1000);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {

                    public void run() {
                        Graphics g=JPanelWorkOfDraw.getGraphics();
                        if(g!=null){
                            //g.setColor(Color.orange);
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowDeactivated");
                        }

                    }
                }, 1000);
            }
        });

        BtnDrawOval.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // 获取事件源（即开关按钮本身）
                JToggleButton toggleBtn = (JToggleButton) e.getSource();
                //System.out.println(toggleBtn.getText() + " 是否选中: " + toggleBtn.isSelected());
            }
        });
//        DrawListen DrawListener=new DrawListen(JPanelWorkOfDraw,TfdFrom.getText(),TfdTo.getText());  //引用JPanelWorkOfDraw对象创建监听对象，此DrawListener对象注册到功能按钮上面，实现对JPanelWorkOfDraw引用的绘图

        BtnDrawOval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == false)
                    flag = true;
                else flag = false;
                System.out.println(flag);

            }
        });//将监听对象DrawListener添加到BtnDrawOval按钮


        JPanelWorkOfDraw.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {    //增加点
                super.mousePressed(e);

                if (flag == true) {
                    String name = String.valueOf(cnt);
                    if (adj.addVertex(name)) {
                        g = JPanelWorkOfDraw.getGraphics();
                        g.setColor(Color.black);
                        g.fillArc(e.getX() - 10, e.getY() - 10, 20, 20, 0, 360);
                        //JPanelWorkOfDraw.getGraphics().drawArc(e.getX() - 10, e.getY() - 10, 20, 20, 0, 360);//画圆
                        pp.put(name, new Point(e.getX(), e.getY(), name));//记录画布x y坐标
                        JPanelWorkOfDraw.getGraphics().drawString(name, e.getX() - 5, e.getY() + 20);//画name
                        cnt++;

                        rt.writeTxtAdjMatrix("adjdata.txt", adj);
                        rt.writeHash("ppdata.txt", pp);
                        rt.writeint("Zcntdata.txt",cnt);

                        System.out.println(cnt);
                        //**********************数据库加点
                        try {
                            Spot spot = new Spot();
                            spot.setSpid(name);
                            spot.setSpname(null);
                            spot.setDescription(null);
                            //调用映射文件定义的接口方法
                            int i = mapper.addSpot(spot);
                            System.out.println(i);
                            if (i > 0) {
                                System.out.println("yep!!!!");
                            } else {
                                System.out.println("NO!!!");
                            }
                            sqlSession.commit();
                        } catch (Exception exception) {
                            System.out.println("添加失败了");
                            sqlSession.rollback(); //回滚
                        } finally {

                        }

                        //*************************

                        //
                    } else {
                        System.out.println("插入失败");
                    }
                    adj.printGraph();
                    /*for (String i : pp.keySet()) {
                        System.out.println("key: " + i + " x: " + pp.get(i).getX() + " y: " + pp.get(i).getY());
                    }*/

                } else {
                    for (String i : pp.keySet()) {
                        /*System.out.println("key: " + i + " x: " + pp.get(i).getX() + " y: " + pp.get(i).getY());
                        System.out.println((e.getY()));*/

                        int a = (((e.getY() - pp.get(i).getY()) * (e.getY() - pp.get(i).getY())) + ((e.getX() - pp.get(i).getX()) * (e.getX() - pp.get(i).getX())));
                        int b = (int) Math.sqrt(a);

                        if (b < 10) {
                            adj.printPanel(JPanelWorkOfDraw, pp);
                            g = JPanelWorkOfDraw.getGraphics();
                            g.setColor(Color.blue);
                            g.fillArc(pp.get(i).getX() - 10, pp.get(i).getY() - 10, 20, 20, 0, 360);

                            String idx = pp.get(i).getName();//String tsb加
                            Spot spot = new Spot();
                            spot = mapper.getSpot(idx);
                            if (spot != null) {
                                //JOptionPane.showMessageDialog(null, "成功", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                                TfdelPoint.setText(spot.getSpid());
                                testId.setText(spot.getSpid());
                                testName.setText(spot.getSpname());
                                Messagedes.setText(spot.getDescription());
                            } else {
                                testId.setText(idx);
                                testName.setText(null);
                                Messagedes.setText(null);
                                JOptionPane.showMessageDialog(null, "查找失败请重新输入", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                            }

                        }

                    }
                }
                int clickTimes = e.getClickCount();
                if (clickTimes == 2) {
                    for (String i : pp.keySet()) {

                        int a = (((e.getY() - pp.get(i).getY()) * (e.getY() - pp.get(i).getY())) + ((e.getX() - pp.get(i).getX()) * (e.getX() - pp.get(i).getX())));
                        int b = (int) Math.sqrt(a);
                        if (b < 10) {
                            g = JPanelWorkOfDraw.getGraphics();
                            g.setColor(Color.BLACK);
                            g.fillArc(pp.get(i).getX() - 10, pp.get(i).getY() - 10, 20, 20, 0, 360);

                            //JPanelWorkOfDraw.getGraphics().fillArc(pp.get(i).getX(),pp.get(i).getY(),20,20,0,360);

                        }


                    }
                }
                //adj.get

            }
        });

        BtnVerify.addActionListener(new ActionListener() {      //增加边
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean a = adj.insertEdge(adj.getrealnum(TfdTo.getText()), adj.getrealnum(TfdFrom.getText()), Integer.parseInt(Tflength.getText()));
                boolean b = adj.insertEdge(adj.getrealnum(TfdFrom.getText()), adj.getrealnum(TfdTo.getText()), Integer.parseInt(Tflength.getText()));
                if (a && b) {

                    JPanelWorkOfDraw.getGraphics().drawLine(pp.get(TfdFrom.getText()).getX(), pp.get(TfdFrom.getText()).getY(), pp.get(TfdTo.getText()).getX(), pp.get(TfdTo.getText()).getY());
                    JPanelWorkOfDraw.getGraphics().drawString(Tflength.getText(), (pp.get(TfdFrom.getText()).getX() + pp.get(TfdTo.getText()).getX()) / 2, (pp.get(TfdFrom.getText()).getY() + pp.get(TfdTo.getText()).getY()) / 2);
                    //adj.printPanel(JPanelWorkOfDraw, pp);

                    rt.writeTxtAdjMatrix("adjdata.txt", adj);
                    rt.writeHash("ppdata.txt", pp);
                    rt.writeint("Zcntdata.txt",cnt);

                } else {
                    System.out.println("插入失败messagebox!!!!!");
                }


                adj.printGraph();
                //清空输入框

                //---------
            }
        });

        BtnDelete.addActionListener(new ActionListener() { //删除边
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean a = adj.removeEdge(adj.getrealnum(TfdFrom.getText()), adj.getrealnum(TfdTo.getText()));
                boolean b = adj.removeEdge(adj.getrealnum(TfdTo.getText()), adj.getrealnum(TfdFrom.getText()));
                if (a && b) {
                    Graphics g = JPanelWorkOfDraw.getGraphics();
                    g.setColor(Color.white);
                    g.fillRect(0, 0, 1500, 617);
//                        JPanelWorkOfDraw.getGraphics().setColor(Color.white);
                    adj.printPanel(JPanelWorkOfDraw, pp);

                    rt.writeTxtAdjMatrix("adjdata.txt", adj);
                    rt.writeHash("ppdata.txt", pp);
                    rt.writeint("Zcntdata.txt",cnt);

                } else {
                    System.out.println("删除失败！！！");
                }

                adj.printGraph();
            }
        });

        Btndel.addActionListener(new ActionListener() {//删除点
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adj.removeVertex(adj.getrealnum(TfdelPoint.getText()))) {
                    pp.remove(TfdelPoint.getText());
                    Graphics g = JPanelWorkOfDraw.getGraphics();
                    g.setColor(Color.white);
                    g.fillRect(0, 0, 1500, 617);
                    adj.printPanel(JPanelWorkOfDraw, pp);

                    rt.writeTxtAdjMatrix("adjdata.txt", adj);
                    rt.writeHash("ppdata.txt", pp);
                    rt.writeint("Zcntdata.txt",cnt);

                    try {
                        int i = mapper.deleteSpot(TfdelPoint.getText());
                        if (i > 0) {
                            System.out.println("删除成功！");
                            TfdelPoint.setText("");
                        } else {
                            System.out.println("操作未完成！");
                        }
                        sqlSession.commit();  //CUD需要以事务方式提交！
                    } catch (Exception exception) {
                    }
                } else {
                    System.out.println("删除失败！！！！");
                }

                adj.printGraph();
            }

        });

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Sstart = TfdStart.getText(), Send = TfdEnd.getText();
                //System.out.println(adj.getrealnum(Sstart));
                //System.out.println(adj.getrealnum(Send));
                Vector<Vector> result = adj.all(adj.getrealnum(Sstart), adj.getrealnum(Send));


                String Sresult = new String("可行路径：\n");
                for (int i = 0; i < result.size(); i++) {
                    System.out.println("第" + (i + 1) + "种可行路径：");
                    for (int j = 1; j < result.get(i).size(); j++) {
                        System.out.print(adj.getrealvertices(Integer.parseInt(result.get(i).get(j).toString())) + " ");
                        Sresult += adj.getrealvertices(Integer.parseInt(result.get(i).get(j).toString())) + " ";
                    }
                    System.out.print("距离： " + result.get(i).get(0).toString());
                    Sresult += "距离： " + result.get(i).get(0).toString();
                    System.out.println();
                    Sresult += "\n";
                }
                if(result.size() == 0)
                    Sresult = "无可行路径";
                Message.setText(Sresult);

            }
        });

        BtnShortest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Sstart = TfdStart.getText(), Send = TfdEnd.getText();
                Vector<Vector> resultshortest = adj.shortestByDfs(adj.getrealnum(Sstart), adj.getrealnum(Send));

                String Sresult = new String("最短路径\n");
                for (int i = 0; i < resultshortest.size(); i++) {
                    System.out.println("第" + (i + 1) + "个最短路径:");

                    Sresult += "第" + (i + 1) + "个最短路径:\n";
                    for (int j = 1; j < resultshortest.get(i).size(); j++) {
                        System.out.print(adj.getrealvertices(Integer.parseInt(resultshortest.get(i).get(j).toString())) + " ");
                        Sresult += adj.getrealvertices(Integer.parseInt(resultshortest.get(i).get(j).toString())) + " ";
                    }
                    System.out.print("距离： " + resultshortest.get(i).get(0));
                    Sresult += "距离： " + resultshortest.get(i).get(0);
                    System.out.println();
                    Sresult += "\n";
                }
                if(resultshortest.size() == 0)
                    Sresult = "无可行路径";
                Message.setText(Sresult);


            }
        });

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idd = testId.getText();//获得testid上的id号数据
                try {
                    Spot spot = new Spot();
                    spot = mapper.getSpot(idd);

                    if (spot != null) {
                        //JOptionPane.showMessageDialog(null, "成功", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                        testName.setText(spot.getSpname());
                        Messagedes.setText(spot.getDescription());
                    } else {
                        JOptionPane.showMessageDialog(null, "查找失败请重新输入", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    //MyBatisUtil.closeSqlSession(sqlSession);
                }
            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idd = testId.getText();//获得testid上的id号数据
                try {
                    Spot spot = new Spot();
                    spot = mapper.getSpot(idd);
                    if (spot != null) {
                        spot.setSpname(testName.getText());
                        spot.setDescription(Messagedes.getText());
                        int i = mapper.updateSpot(spot);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(null, "成功", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                        } else {
                            JOptionPane.showMessageDialog(null, "修改失败", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                        }
                        sqlSession.commit();  //CUD需要以事务方式提交！
                    } else {
                        JOptionPane.showMessageDialog(null, "没有此id", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    //MyBatisUtil.closeSqlSession(sqlSession);
                }
            }
        });

        JMenuChangeUser.addMouseListener(new MouseAdapter() {  //给JMenuNew添加一个事件监听器
            public void mouseClicked(MouseEvent e) {   //切换到登录界面
                new Login();
                dispose();
            }
        });




    }
}
