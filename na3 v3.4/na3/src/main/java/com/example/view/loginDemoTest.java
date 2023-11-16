package com.example.view;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tan Shaobin
 * @Date: 2022/11/29/19:20
 * @Description:
 */
public class loginDemoTest {

    public static void main(String[] args) {
        Login logining=new Login();
        JFrame frame = new JFrame("Login");
        frame.setContentPane(logining.Loginform);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
