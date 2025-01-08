package com.zyz.ui;

import com.zyz.bean.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEmployeeUI extends JDialog {
    private JTextField nameField;
    private JTextField genderField;
    private JTextField ageField;
    private JTextField jobField;
    private JTextField departmentField;
    private JTextField entryTimeField;
    private JButton saveButton;
    private Employee employee;
    private EmployeeInfoUI parentUI;

    public EditEmployeeUI(EmployeeInfoUI parentUI, Employee employee) {
        super(parentUI, "编辑员工", true);
        this.parentUI = parentUI;
        this.employee = employee;

        setSize(500, 350); // 增大对话框尺寸
        setLocationRelativeTo(parentUI);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 使用 GridBagLayout 以便更精确地控制布局
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // 设置组件间距
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 输入框设置为水平填充

        // 员工ID (不可编辑)
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("员工ID:"), gbc);

        JTextField idField = new JTextField(String.valueOf(employee.getId()));
        idField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 5;  // 员工ID占两列
        add(idField, gbc);

        // 姓名
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("姓名:"), gbc);
        nameField = new JTextField(employee.getName(), 20);
        gbc.gridx = 1;
        gbc.gridwidth = 5;  // 姓名输入框占两列
        add(nameField, gbc);

        // 性别
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("性别:"), gbc);
        genderField = new JTextField(employee.getGender(), 20);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        add(genderField, gbc);

        // 年龄
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("年龄:"), gbc);
        ageField = new JTextField(String.valueOf(employee.getAge()), 20);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        add(ageField, gbc);

        // 职务
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("职务:"), gbc);
        jobField = new JTextField(employee.getJob(), 20);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        add(jobField, gbc);

        // 部门
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("部门:"), gbc);
        departmentField = new JTextField(employee.getDepartment(), 20);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        add(departmentField, gbc);

        // 入职时间
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("入职时间:"), gbc);
        entryTimeField = new JTextField(employee.getEntryTime(), 20);
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        add(entryTimeField, gbc);

        // 保存按钮
        saveButton = new JButton("保存");
        saveButton.setBackground(new Color(34, 139, 34)); // 设置按钮颜色
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        saveButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;  // 保存按钮跨越两列
        gbc.fill = GridBagConstraints.HORIZONTAL;  // 按钮占满整个宽度
        add(saveButton, gbc);

        // 保存按钮事件
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 更新员工信息
                employee.setName(nameField.getText());
                employee.setGender(genderField.getText());
                employee.setAge(Integer.parseInt(ageField.getText()));
                employee.setJob(jobField.getText());
                employee.setDepartment(departmentField.getText());
                employee.setEntryTime(entryTimeField.getText());

                // 关闭对话框
                parentUI.updateEmployeeTable();
                dispose();
            }
        });

        setVisible(true);
    }
}
