package com.zyz.ui;

import com.zyz.bean.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeUI extends JFrame {
    // 定义表单字段
    private JTextField employeeIdField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField jobTitleField;
    private JTextField departmentField;
    private JTextField joinDateField;
    private JComboBox<String> genderComboBox;
    private EmployeeInfoUI employeeInfoUI;

    public AddEmployeeUI(EmployeeInfoUI employeeInfoUI) {
        this.employeeInfoUI = employeeInfoUI;
        // 设置窗口标题
        setTitle("添加员工信息");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建面板并设置布局
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // 设置边距

        // 公司名标签
        JLabel titleLabel = new JLabel("添加员工信息", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 150, 243));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // 使用GridBagLayout来布置表单字段
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));  // 7行2列，设置行间距和列间距

        // 初始化表单字段
        JLabel employeeIdLabel = new JLabel("员工ID:");
        employeeIdField = new JTextField();
        setInputStyle(employeeIdField);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField();
        setInputStyle(nameField);

        JLabel genderLabel = new JLabel("性别:");
        genderComboBox = new JComboBox<>(new String[]{"男", "女"});
        setInputStyle(genderComboBox);

        JLabel ageLabel = new JLabel("年龄:");
        ageField = new JTextField();
        setInputStyle(ageField);

        JLabel jobTitleLabel = new JLabel("职务:");
        jobTitleField = new JTextField();
        setInputStyle(jobTitleField);

        JLabel departmentLabel = new JLabel("部门:");
        departmentField = new JTextField();
        setInputStyle(departmentField);

        JLabel joinDateLabel = new JLabel("入职时间:");
        joinDateField = new JTextField();  // 格式：yyyy-MM-dd
        setInputStyle(joinDateField);

        // 添加表单字段到formPanel
        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(genderLabel);
        formPanel.add(genderComboBox);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(jobTitleLabel);
        formPanel.add(jobTitleField);
        formPanel.add(departmentLabel);
        formPanel.add(departmentField);
        formPanel.add(joinDateLabel);
        formPanel.add(joinDateField);

        // 将formPanel添加到主面板
        panel.add(formPanel);

        // 创建提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.setBackground(new Color(40, 167, 69));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐

        // 按钮点击事件
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取表单输入的数据
                String employeeId = employeeIdField.getText();
                String name = nameField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                String age = ageField.getText();
                String jobTitle = jobTitleField.getText();
                String department = departmentField.getText();
                String joinDate = joinDateField.getText();

                // 简单的表单验证（例如，检查是否有字段为空）
                if (employeeId.isEmpty() || name.isEmpty() || age.isEmpty() || jobTitle.isEmpty() ||
                        department.isEmpty() || joinDate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请填写所有信息", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    // 创建员工对象并将信息添加到系统中（例如，将其保存到数据库或内存中）
                    Employee newEmployee = new Employee(Integer.parseInt(employeeId), name, gender, Integer.parseInt(age),
                            jobTitle, department, joinDate);
                    employeeInfoUI.addEmployee(newEmployee);

                    // 输出到控制台，模拟保存数据（实际项目中可将其保存到数据库或文件）
                    System.out.println("新增员工信息：" + newEmployee);

                    // 提示用户成功
                    JOptionPane.showMessageDialog(null, "员工信息添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);

                    // 清空表单
                    clearForm();
                }
            }
        });

        // 将按钮添加到面板
        panel.add(submitButton);

        // 将面板添加到窗口
        add(panel);

        // 窗口可见
        setVisible(true);
    }

    // 设置输入框的样式
    private void setInputStyle(JComponent component) {
        component.setFont(new Font("微软雅黑", Font.PLAIN, 14));  // 设置字体
        component.setBackground(new Color(242, 242, 242));  // 设置背景色
        component.setForeground(new Color(51, 51, 51));  // 设置字体颜色
        component.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));  // 设置边框
    }

    // 清空表单数据
    private void clearForm() {
        employeeIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        jobTitleField.setText("");
        departmentField.setText("");
        joinDateField.setText("");
    }

}
