package com.zyz.ui;

import com.zyz.bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI extends JFrame {

    // 构造函数
    public RegisterUI(LoginUI loginUI) {
        // 设置窗口标题和大小
        setTitle("用户注册");
        setSize(400, 250);  // 调整窗口的高度使其更加紧凑
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 15, 15));  // 适当增加间隔，使布局更加整齐

        // 设置字体和颜色
        Font labelFont = new Font("微软雅黑", Font.PLAIN, 14);

        // 用户名输入框
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(labelFont);
        JTextField usernameField = new JTextField();

        // 昵称输入框
        JLabel nicknameLabel = new JLabel("昵称:");
        nicknameLabel.setFont(labelFont);
        JTextField nicknameField = new JTextField();

        // 密码输入框 (不隐藏文本)
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(labelFont);
        JTextField passwordField = new JTextField();  // 使用 JTextField 来显示输入的内容

        // 注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBackground(new Color(34, 139, 34));  // 设置按钮背景色
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("微软雅黑", Font.BOLD, 14));

        // 添加组件到面板
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(nicknameLabel);
        panel.add(nicknameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // 占位，避免按钮被拉伸
        panel.add(registerButton);

        // 设置面板边距
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 按钮点击事件
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String nickname = nicknameField.getText();
                String password = passwordField.getText();  // 获取密码文本

                // 简单的非空验证
                if (username.isEmpty() || nickname.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "所有字段都必须填写", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    User newuser = new User(username, nickname, password);
                    // 这里可以添加注册逻辑
                    JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    loginUI.addUser(newuser);
                    //关闭页面，显示登录页面
                    dispose();
                    //loginUI.setVisible(true);
                }
            }
        });

        // 将面板添加到窗口
        add(panel);

        // 窗口可见
        setVisible(true);
    }

}
