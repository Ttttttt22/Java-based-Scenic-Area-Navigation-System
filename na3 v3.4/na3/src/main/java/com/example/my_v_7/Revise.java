package com.example.my_v_7;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;

public class Revise extends JFrame {

    //测试
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static UserMapper mapper=null;  //定义映射器接口类型的对象

    static {
        sqlSession= MyBatisUtil.getSqlSession();
        mapper=sqlSession.getMapper(UserMapper.class);
    }
    //

    public Revise(){
        JFrame revise =new JFrame("修改密码");
        revise.setSize(350,300);
        revise.setLocationRelativeTo(null);
        JPanel root=new JPanel();//面板
        root.setLayout(null);
        revise.setContentPane(root);

        JLabel userLabel = new JLabel("User:");//用户标签
        userLabel.setBounds(10,20,80,25);
        revise.add(userLabel);

        JTextField userText = new JTextField(20);//用户名输入文本框
        userText.setBounds(120,20,165,25);
        revise.add(userText);

        JLabel passwordLabel = new JLabel("NewPassword:");//密码标签
        passwordLabel.setBounds(10,60,100,25);
        revise.add(passwordLabel);

        JPasswordField NewpasswordText = new JPasswordField(20);//密码文本框
        NewpasswordText.setBounds(120,60,165,25);
        revise.add(NewpasswordText);

        JLabel PhoneNumberLabel= new JLabel("PhoneNumber:");//手机号码标签
        PhoneNumberLabel.setBounds(10,100,90,25);
        revise.add(PhoneNumberLabel);

        JTextField PhoneNumberText = new JTextField(20);//手机号码文本框
        PhoneNumberText.setBounds(120,100,165,25);
        revise.add(PhoneNumberText);

        revise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        revise.setVisible(true);

        JButton ConfirmButton =new JButton("确认");//确认按钮
        ConfirmButton.setBounds(60, 200, 80, 25);
        revise.add(ConfirmButton);

        JButton CancelButton =new JButton("取消");//取消按钮
        CancelButton.setBounds(170, 200, 80, 25);
        revise.add(CancelButton);

        ConfirmButton.addActionListener(e -> {
            String admin = userText.getText();//用户名
            char[] Newpassword = NewpasswordText.getPassword();//密码
            String str1 = String.valueOf(Newpassword);//转化

            User user=mapper.getUserByUsername(admin);
            if(user!=null&&PhoneNumberText.getText().equals(user.getPhonenumber())){
                user.setPassword(str1);
                int i= mapper.updateUser(user);
                if(i>0) {
                    JOptionPane.showMessageDialog(null, "修改密码成功！", "提示框", JOptionPane.OK_CANCEL_OPTION);//提示框
                    new Login();//返回登录界面
                    revise.dispose();//关闭注册界面
                }else {
                    System.out.println("操作未完成！");//失败的框
                }
                sqlSession.commit();  //CUD需要以事务方式提交！
            }
            else {
                JOptionPane.showMessageDialog(null, "用户名不存在或者电话号码错误", "提示框", JOptionPane.ERROR_MESSAGE);//提示框
                userText.setText(null);//清零
                NewpasswordText.setText(null);//清零
            }


        });
        CancelButton.addActionListener(e -> {
            new Login();
            revise.dispose();//关闭注册界面

        });

    }


}
