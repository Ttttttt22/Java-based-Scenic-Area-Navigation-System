package com.example.my_v_7;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;

import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;

public class Register extends JFrame {
    //测试
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static UserMapper mapper=null;  //定义映射器接口类型的对象

    static {
        sqlSession= MyBatisUtil.getSqlSession();
        mapper=sqlSession.getMapper(UserMapper.class);
    }
    //

    public Register(){

    JFrame register =new JFrame();
        register.setSize(300,300);
        register.setLocationRelativeTo(null);
        JPanel root=new JPanel();//面板
        root.setLayout(null);
        register.setContentPane(root);

        JLabel userLabel = new JLabel("User:");//用户标签
        userLabel.setBounds(10,20,80,25);
        register.add(userLabel);

        JTextField userText = new JTextField(20);//用户名输入文本框
        userText.setBounds(100,20,165,25);
        register.add(userText);

        JLabel passwordLabel = new JLabel("Password:");//密码标签
        passwordLabel.setBounds(10,60,80,25);
        root.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);//密码文本框
        passwordText.setBounds(100,60,165,25);
        register.add(passwordText);

        JLabel RealNametLable= new JLabel("RealName:");//真实姓名标签
        RealNametLable.setBounds(10,100,80,25);
        root.add(RealNametLable);

        JTextField RealNameText = new JTextField(20);//真实姓名文本框
        RealNameText.setBounds(100,100,165,25);
        register.add(RealNameText);

        JLabel PhoneNumberLabel= new JLabel("PhoneNumber:");//手机号码标签
        PhoneNumberLabel.setBounds(10,150,90,25);
        root.add(PhoneNumberLabel);

        JTextField PhoneNumberText = new JTextField(20);//手机号码文本框
        PhoneNumberText.setBounds(100,150,165,25);
        register.add(PhoneNumberText);

        register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register.setVisible(true);

        JButton ConfirmButton =new JButton("确认");//确认按钮
        ConfirmButton.setBounds(60, 200, 80, 25);
        register.add(ConfirmButton);

        JButton CancelButton =new JButton("取消");//取消按钮
        CancelButton.setBounds(150, 200, 80, 25);
        register.add(CancelButton);

        ConfirmButton.addActionListener(e -> {
            //设置值
            User user =new User();
            char[] pw11=passwordText.getPassword();
            String pw1 = String.valueOf(pw11);//转化
            user.setUsername(userText.getText());user.setPassword(pw1);user.setPhonenumber(PhoneNumberText.getText());user.setRealname(RealNameText.getText());

            //调用映射文件定义的接口方法
            int i=mapper.addUser(user);
            System.out.println(i);
            if(i>0){
                JOptionPane.showMessageDialog(null, "注册成功!", "提示框", JOptionPane.OK_CANCEL_OPTION);//提示框
                sqlSession.commit();
                new Login();//返回登录界面
                register.dispose();//关闭注册界面
            }
            else{
                JOptionPane.showMessageDialog(null, "用户名已经存在请设置新的用户名", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
            }
        });
        //取消按钮的点击事件
        CancelButton.addActionListener(e -> {
            new Login();
            register.dispose();//关闭注册界面
        });

    }}
