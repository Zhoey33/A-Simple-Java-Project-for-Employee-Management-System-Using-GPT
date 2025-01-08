package com.zyz.ui;

import com.zyz.bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginUI extends JFrame implements ActionListener{
    //静态ArrayList，存储用户的登录信息，类是User
    private static ArrayList<User> users = new ArrayList<>();
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("登录");
    private JButton registerButton = new JButton("注册");

    // 构造函数
    public LoginUI() {
        // 设置窗口标题
        setTitle("公司人员管理系统 - 登录");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板并设置布局管理器
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 公司名标签
        JLabel companyNameLabel = new JLabel("ZZZ公司人员管理系统", JLabel.CENTER);
        companyNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));  // 使用中文支持字体
        companyNameLabel.setForeground(Color.BLUE);

        // 设置 GridBagLayout 中的约束
        gbc.gridwidth = 2;  // 让公司名称标签占据两列
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;  // 水平居中
        panel.add(companyNameLabel, gbc);

        // 用户名标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        JTextField usernameField = this.usernameField;

        // 密码标签和密码框
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = this.passwordField;

        // 布局设置
        gbc.gridwidth = 1;  // 恢复每个组件占据一列
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;  // 标签对齐到右侧
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;  // 输入框对齐到左侧
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;  // 标签对齐到右侧
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;  // 输入框对齐到左侧
        panel.add(passwordField, gbc);

        // 登录按钮
        JButton loginButton = this.loginButton;
        loginButton.setBackground(new Color(0, 123, 255));  // 设置按钮颜色
        loginButton.setForeground(Color.WHITE);  // 设置按钮字体颜色
        loginButton.setFocusPainted(false);  // 去掉按钮的焦点边框
        loginButton.addActionListener(this);

        // 注册按钮
        JButton registerButton = this.registerButton;
        registerButton.setBackground(new Color(40, 167, 69));  // 设置注册按钮颜色
        registerButton.setForeground(Color.WHITE);  // 设置按钮字体颜色
        registerButton.setFocusPainted(false);  // 去掉按钮的焦点边框
        registerButton.addActionListener(this);

        // 登录和注册按钮放在一行
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // 将面板添加到窗口
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //判断是登录还是注册
        if (e.getSource() == loginButton) {
            // 拿到用户名和密码
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // 判断用户名是否在集合中，不在则输出用户名不存在
            boolean userExists = false;
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    userExists = true;
                    // 判断密码是否正确，不正确则输出密码错误，正确则跳转到 EmployeeInfoUI 界面
                    if (user.getPassword().equals(password)) {
                        //JOptionPane.showMessageDialog(this, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                        // 跳转到 EmployeeInfoUI 界面
                        String loginUerNameNickname = user.getNickname();
                        new EmployeeInfoUI(loginUerNameNickname);  // 假设你有一个 EmployeeInfoUI 界面
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // 如果用户名不存在
            if (!userExists) {
                JOptionPane.showMessageDialog(this, "用户名不存在！", "错误", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == registerButton) {
            //弹出提示框，你点击了注册
            new RegisterUI(this);
        }
    }

    public void addUser(User user) {
        users.add(user);
    }
}
