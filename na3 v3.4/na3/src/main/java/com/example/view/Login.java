package com.example.view;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Login {
    private JTextField textField1;
    private JTextField textField2;
    public JButton dengluButton;
    public JPanel Loginform;

    //测试
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static UserMapper mapper=null;  //定义映射器接口类型的对象

    static {
        sqlSession= MyBatisUtil.getSqlSession();
        mapper=sqlSession.getMapper(UserMapper.class);
    }
    //

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().Loginform);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Login() {
        dengluButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un1=textField1.getText();
                String pw1=textField2.getText();
                List<User> users=mapper.getAllUsers();
                boolean flag=false;

                for (User user : users) {
                    String un=user.getUsername();
                    String pw=user.getPassword();
                    if(un1.equals(un)&&pw.equals(pw1)){
                        flag=true;
                    }
                }

                if(flag)System.out.println("成功登录");
                else System.out.println("登录失败");
            }
        });
    }



}
