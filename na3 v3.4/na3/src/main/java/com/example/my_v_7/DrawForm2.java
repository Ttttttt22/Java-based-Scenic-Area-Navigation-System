package com.example.my_v_7;


import com.example.dao.entity.Spot;
import com.example.dao.mapper.SpotMapper;
import com.example.t_v_2.ReadTxt;
import com.example.t_v_2.TriTuple;
import com.example.t_v_2.adjMatrix;
import com.example.util.MyBatisUtil;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DrawForm2<hashMap> extends JFrame {

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

//
    JTextField testId;
    JTextField testName;
    JScrollPane ma;
    JLabel Lbstart;
    private JTextField TfdStart;
    JLabel Lbend;  //定义BtnTo按钮
    private JTextField TfdEnd;
    JButton calculate;//
    JButton BtnShortest;
    JButton Search;
    JButton change;
    private JTextField TfdelPoint;//删除点按键
    JTextArea Message;
    JScrollPane ma1;
    JTextArea Messagedes;
    JPanel JPanelWorkPlace;  //定义JPanelWorkPlace,用来放JPanelWorkOfLeft和JPanelWorkOfDraw还有JPanelWorkRight
    JPanel JPanelWorkOfLeft;  //定义JPanelWorkOfLeft用来放 选择绘图的哪个图案的按钮
    JPanel JPanelWorkRight;//定义JPanelWorkRight放

    JPanel JPanelWorkOfDraw;  //定义JPanelWorkOfDraw，用做画图面板

    //--------
    HashMap<String, Point> pp = new HashMap<String, Point>();
    HashMap<String, TriTuple> ee = new HashMap<String, TriTuple>();

    adjMatrix adj = new adjMatrix(0, 0);

    JFileChooser fileChooser;
    ReadTxt rt = new ReadTxt();
    //-----

    int cnt = 1;
    boolean cflag = true;



    public DrawForm2(String title) {  //构造函数初始化

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
                    if(cflag == true)
                        adj.printPanelname(JPanelWorkOfDraw, pp);
                    else
                        adj.printPanel(JPanelWorkOfDraw, pp);
                    //adj.printPanelname(JPanelWorkOfDraw, pp);
                }

            }
        }, 1000);

    }

    private void InitComponent() {   //初始化窗体组件函数
        JMenubar = new JMenuBar();
        JMenuChangeUser = new JMenu("切换用户");




        JPanelTop = new JPanel();
        JPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1)); //设置JPanelTop的布局为FlowLayout.CENTER
        JPanelTop.setBorder(new LineBorder(new Color(0, 0, 0)));  //设置JPanelTop的边框
        JPanelTop.setBounds(0, 0, 1500, 40);  //设置JPanelTop的位置，上层容器布局需要设置为null布局




        calculate = new JButton("calculate");
        calculate.setBackground(new Color(225, 225, 225));
        calculate.setPreferredSize(new Dimension(100, 28));



        BtnShortest = new JButton("Shortest");
        BtnShortest.setBackground(new Color(225, 225, 225));
        BtnShortest.setPreferredSize(new Dimension(100, 28));

        change = new JButton("change");
        change.setBackground(new Color(225, 225, 225));
        change.setPreferredSize(new Dimension(100, 28));


        Lbstart = new JLabel("start");
        Lbstart.setBackground(new Color(225, 225, 225));
        Lbstart.setPreferredSize(new Dimension(67, 28));
        Lbstart.setHorizontalAlignment(JTextField.RIGHT);
        Lbend = new JLabel("To");
        Lbend.setBackground(new Color(225, 225, 225));
        Lbend.setPreferredSize(new Dimension(67, 28));
        Lbend.setHorizontalAlignment(JTextField.RIGHT);



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
        ma1=new JScrollPane(JPanelWorkRight);
        ma1.setBounds(1280,10,210,590);
        ma1.setHorizontalScrollBarPolicy(ma.HORIZONTAL_SCROLLBAR_NEVER);



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






    }

    private void AddComponent() {  //添加组件方法
        JMenubar.add(JMenuChangeUser);   //把菜单“切换用户”加到菜单条

        setJMenuBar(JMenubar);    //设置窗体菜单条

        JPanelTop.add(change);
        JPanelTop.add(Lbstart);
        JPanelTop.add(TfdStart);
        JPanelTop.add(Lbend);
        JPanelTop.add(TfdEnd);
        JPanelTop.add(calculate);
        JPanelTop.add(BtnShortest);

        JPanelWorkOfLeft.add(Search);

        JPanelWorkOfLeft.add(testId);
        JPanelWorkOfLeft.add(testName);
        JPanelWorkOfLeft.add(Messagedes);
        JPanelWorkRight.add(Message);
        JPanelWorkPlace.add(JPanelWorkOfLeft);  //把左侧功能选择和画图区域组合起来加到JPanelWorkPlace容器中
        JPanelWorkPlace.add(JPanelWorkOfDraw);
        JPanelWorkPlace.add(ma1);
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
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
                            if(cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
                                adj.printPanel(JPanelWorkOfDraw, pp);
                            System.out.println("windowDeactivated");
                        }

                    }
                }, 1000);
            }
        });


        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = JPanelWorkOfDraw.getGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, 1500, 617);
                if(cflag == true){
                    cflag = false;
                    adj.printPanel(JPanelWorkOfDraw, pp);

                }
                else{
                    cflag = true;
                    adj.printPanelname(JPanelWorkOfDraw, pp);

                }

                System.out.println("cflag:" + cflag);
            }
        });

        JPanelWorkOfDraw.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {    //增加点
                super.mousePressed(e);


                    for (String i : pp.keySet()) {
                        /*System.out.println("key: " + i + " x: " + pp.get(i).getX() + " y: " + pp.get(i).getY());
                        System.out.println((e.getY()));*/

                        int a = (((e.getY() - pp.get(i).getY()) * (e.getY() - pp.get(i).getY())) + ((e.getX() - pp.get(i).getX()) * (e.getX() - pp.get(i).getX())));
                        int b = (int) Math.sqrt(a);

                        if (b < 10) {
                            if (cflag == true)
                                adj.printPanelname(JPanelWorkOfDraw, pp);
                            else
                                adj.printPanel(JPanelWorkOfDraw, pp);
                            g = JPanelWorkOfDraw.getGraphics();
                            g.setColor(Color.blue);
                            g.fillArc(pp.get(i).getX() - 10, pp.get(i).getY() - 10, 20, 20, 0, 360);

                            String idx = pp.get(i).getName();//String tsb加
                            Spot spot1 = new Spot();
                            List<Spot> spots = mapper.getAllSpots();
                            for (Spot spot : spots) {
                                if (spot.getSpid().equals(idx)) {
                                    spot1 = spot;
                                }
                            }

                            if (spot1 != null) {
                                //JOptionPane.showMessageDialog(null, "成功", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                                //TfdelPoint.setText(spot.getSpid());
                                testId.setText(spot1.getSpid());
                                testName.setText(spot1.getSpname());
                                Messagedes.setText(spot1.getDescription());
                            } else {
                                testId.setText(idx);
                                testName.setText(null);
                                Messagedes.setText(null);
                                JOptionPane.showMessageDialog(null, "查找失败请重新输入", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                            }

                        }

                    }


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

                String Sresult2 = new String("最短路径\n");
                for (int i = 0; i < resultshortest.size(); i++) {
                    for (int j = 1; j < resultshortest.get(i).size(); j++) {

                        String dix=adj.getrealvertices(Integer.parseInt(resultshortest.get(i).get(j).toString()));
                        Spot spot1=new Spot();
                        List<Spot> spots=mapper.getAllSpots();
                        for (Spot spot : spots) {
                            if(spot.getSpid().equals(dix))spot1=spot;
                        }
                        Sresult2 +=spot1.getSpname()+" ";
                    }

                    Sresult2 += "距离： " + resultshortest.get(i).get(0);

                    Sresult2 += "\n";
                }
                if(resultshortest.size() == 0)
                    Sresult = "无可行路径";


                Message.setText(Sresult + Sresult2);


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




        JMenuChangeUser.addMouseListener(new MouseAdapter() {  //给JMenuNew添加一个事件监听器
            public void mouseClicked(MouseEvent e) {   //切换到登录界面
                new Login();
                dispose();
            }
        });


    }
}