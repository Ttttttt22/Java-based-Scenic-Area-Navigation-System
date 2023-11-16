package com.example.my_v_7;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;

import com.example.my_v_7.Register;
import com.example.my_v_7.Revise;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.List;


public class Login extends JFrame {

    //测试
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static UserMapper mapper = null;  //定义映射器接口类型的对象

    static {
        sqlSession = MyBatisUtil.getSqlSession();
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    //
    public Login() {
        JFrame frame = new JFrame("登录");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel();//面板
        root.setLayout(null);
        frame.setContentPane(root);


        JLabel userLabel = new JLabel("User:");//用户标签
        userLabel.setBounds(10, 50, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField(20);//用户名输入文本框
        userText.setBounds(100, 50, 165, 25);
        frame.add(userText);


        JLabel passwordLabel = new JLabel("Password:");//密码标签
        passwordLabel.setBounds(10, 100, 80, 25);
        root.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);//密码文本框
        passwordText.setBounds(100, 100, 165, 25);
        root.add(passwordText);


        JButton LoginButton = new JButton("登录");//登录按钮
        LoginButton.setBounds(110, 150, 80, 25);
        frame.add(LoginButton);

        JButton ReviseButton = new JButton("修改密码");//修改密码按钮
        ReviseButton.setBounds(150, 180, 100, 25);
        frame.add(ReviseButton);

        JButton RegisterButton = new JButton("注册");//修改密码按钮
        RegisterButton.setBounds(40, 180, 100, 25);
        frame.add(RegisterButton);


        String a = "123";//**测试用户名用的数据，后续删掉换数据库传入
        String b = "1234";//**测试密码用的数据，后续删掉换数据库传入


        LoginButton.addActionListener(e -> {
                    //传入用户名与密码的处理
                        String un1 = userText.getText();
                        char[] pw11 = passwordText.getPassword();
                        String pw1 = String.valueOf(pw11);//转化
                        //校验用户名与密码
                        List<User> users = mapper.getAllUsers();
                        boolean flag = false;

                        for (User user : users) {
                            String un = user.getUsername();
                            String pw = user.getPassword();
                            if (un1.equals(un) && pw.equals(pw1)) {
                                flag = true;
                            }
                        }
                        if (un1.equals("lt") && pw1.equals("123")) {
                            DrawForm Draw = new DrawForm("管理员界面");
                            Draw.setVisible(true);
                            frame.dispose();//关闭登录界面
                        } else {
                            if (flag) {
                                DrawForm2 Draw = new DrawForm2("导航");
                                Draw.setVisible(true);
                                frame.dispose();//关闭登录界面
                            } else {
                                JOptionPane.showMessageDialog(null, "用户名不存在或密码错误", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                                userText.setText(null);//清零
                                passwordText.setText(null);//清零
                            }
                        }
                    }

        );
        RegisterButton.addActionListener(e -> {
            new Register();
            frame.dispose();
        });

        ReviseButton.addActionListener(e -> {

            new Revise();
            frame.dispose();

        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
