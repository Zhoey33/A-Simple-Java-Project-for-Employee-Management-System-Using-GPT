## 项目需求
1、实现登录、注册功能呢
2、能够管理公司员工的信息，包括添加员工信息、编辑员工信息、删除员工信息

## 实现对象
1、员工对象，存储员工信息。信息有，员工ID、姓名、性别、年龄、职务、部门、入职时间
2、登录对象，实现登录者的相关功能。

## 开发UI界面
### 1.登录界面开发
使用GPT生成登录界面的swing代码：你好，现在你是一位资深的java开发工程师，我现在在做一个公司人员管理系统，我想用swing做ui，请你帮我写一个类。功能是实现登录界面的ui，要求展示公司名，用户输入用户名和密码登录，并有登录和注册两个选项。

gpt生成代码：
```java
package com.zyz.ui;  
  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
public class LoginUI extends JFrame {  
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
        JTextField usernameField = new JTextField(20);  
  
        // 密码标签和密码框  
        JLabel passwordLabel = new JLabel("密码:");  
        JPasswordField passwordField = new JPasswordField(20);  
  
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
        JButton loginButton = new JButton("登录");  
        loginButton.setBackground(new Color(0, 123, 255));  // 设置按钮颜色  
        loginButton.setForeground(Color.WHITE);  // 设置按钮字体颜色  
        loginButton.setFocusPainted(false);  // 去掉按钮的焦点边框  
        loginButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                String username = usernameField.getText();  
                String password = new String(passwordField.getPassword());  
                // 这里可以添加用户名和密码的验证逻辑  
                JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);  
            }  
        });  
  
        // 注册按钮  
        JButton registerButton = new JButton("注册");  
        registerButton.setBackground(new Color(40, 167, 69));  // 设置注册按钮颜色  
        registerButton.setForeground(Color.WHITE);  // 设置按钮字体颜色  
        registerButton.setFocusPainted(false);  // 去掉按钮的焦点边框  
        registerButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // 这里可以跳转到注册界面或执行注册逻辑  
                JOptionPane.showMessageDialog(null, "注册功能待开发", "提示", JOptionPane.INFORMATION_MESSAGE);  
            }  
        });  
  
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
}
```

### 2.员工信息界面
使用gpt生成代码：请继续给我生成一个员工信息界面，要求如下：顶部需要有搜索功能以及添加员工信息按钮。下面展示所有员工的信息，包括员工ID、姓名、性别、年龄、职务、部门、入职时间。在员工信息上点击右键，即可出现编辑和删除两个选项，这两个选项都需要拿到员工的ID。

```java
package com.zyz.ui;  
  
import javax.swing.*;  
import javax.swing.table.DefaultTableCellRenderer;  
import javax.swing.table.DefaultTableModel;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
public class EmployeeInfoUI extends JFrame {  
    private JTable employeeTable;  
    private DefaultTableModel tableModel;  
  
    // 构造函数  
    public EmployeeInfoUI() {  
        // 设置窗口标题和大小  
        setTitle("员工信息管理");  
        setSize(900, 500);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);  
  
        // 创建主面板  
        JPanel panel = new JPanel();  
        panel.setLayout(new BorderLayout());  
  
        // 顶部搜索和添加员工按钮  
        JPanel topPanel = new JPanel();  
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));  
  
        // 设置搜索框  
        JTextField searchField = new JTextField(20);  
        searchField.setFont(new Font("微软雅黑", Font.PLAIN, 14));  
  
        // 设置按钮  
        JButton searchButton = new JButton("搜索");  
        searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));  
        searchButton.setBackground(new Color(70, 130, 180));  // 设置按钮颜色  
        searchButton.setForeground(Color.WHITE);  
        searchButton.setFocusPainted(false);  
  
        JButton addButton = new JButton("添加员工");  
        addButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));  
        addButton.setBackground(new Color(34, 139, 34));  // 设置按钮颜色  
        addButton.setForeground(Color.WHITE);  
        addButton.setFocusPainted(false);  
  
        // 搜索按钮监听  
        searchButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                //弹出框显示搜索功能待开发  
                JOptionPane.showMessageDialog(null, "搜索功能待开发", "提示", JOptionPane.INFORMATION_MESSAGE);  
            }  
        });  
  
        // 添加员工按钮监听  
        addButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // 这里暂时不实现弹出添加员工的界面  
                JOptionPane.showMessageDialog(null, "添加员工功能待开发", "提示", JOptionPane.INFORMATION_MESSAGE);  
            }  
        });  
  
        // 将组件加入顶部面板  
        topPanel.add(searchField);  
        topPanel.add(searchButton);  
        topPanel.add(addButton);  
  
        // 表格模型  
        tableModel = new DefaultTableModel(new Object[]{"员工ID", "姓名", "性别", "年龄", "职务", "部门", "入职时间"}, 0);  
        employeeTable = new JTable(tableModel);  
        populateTable();  
  
        // 设置表格样式  
        employeeTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));  
        employeeTable.setRowHeight(30);  
        employeeTable.setSelectionBackground(new Color(173, 216, 230)); // 设置选中行背景色  
        employeeTable.setSelectionForeground(Color.BLACK);  // 设置选中行文本颜色  
        employeeTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 16));  // 设置表头字体  
  
        // 设置所有列内容居中  
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();  
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  
        for (int i = 0; i < employeeTable.getColumnCount(); i++) {  
            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);  
        }  
  
        // 设置右键菜单  
        JPopupMenu rightClickMenu = new JPopupMenu();  
        JMenuItem editMenuItem = new JMenuItem("编辑");  
        JMenuItem deleteMenuItem = new JMenuItem("删除");  
  
        // 编辑菜单项监听  
        editMenuItem.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // 弹出显示框，显示该功能暂未开发，并显示目标员工ID  
                JOptionPane.showMessageDialog(null, "编辑员工功能待开发\n目标员工ID：" + employeeTable.getValueAt(employeeTable.getSelectedRow(), 0), "提示", JOptionPane.INFORMATION_MESSAGE);  
            }  
        });  
  
        // 删除菜单项监听  
        deleteMenuItem.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // 弹出显示框，显示该功能暂未开发  
                JOptionPane.showMessageDialog(null, "删除员工功能待开发", "提示", JOptionPane.INFORMATION_MESSAGE);  
            }  
        });  
  
        // 添加菜单项到右键菜单  
        rightClickMenu.add(editMenuItem);  
        rightClickMenu.add(deleteMenuItem);  
  
        // 右键点击监听  
        employeeTable.setComponentPopupMenu(rightClickMenu);  
  
        // 表格滚动面板  
        JScrollPane scrollPane = new JScrollPane(employeeTable);  
  
        // 添加组件  
        panel.add(topPanel, BorderLayout.NORTH);  
        panel.add(scrollPane, BorderLayout.CENTER);  
  
        // 将面板添加到窗口  
        add(panel);  
  
        // 窗口可见  
        setVisible(true);  
    }  
  
    // 填充表格  
    private void populateTable() {  
        // 添加一些示例数据  
        tableModel.addRow(new Object[]{1, "张三", "男", 28, "经理", "销售部", "2022-01-01"});  
        tableModel.addRow(new Object[]{2, "李四", "女", 25, "工程师", "研发部", "2023-03-15"});  
        tableModel.addRow(new Object[]{3, "王五", "男", 30, "主管", "市场部", "2020-07-12"});  
    }  
  
    // 过滤员工列表（搜索功能）  
    private void filterEmployeeList(String searchQuery) {  
  
    }  
  
    // 编辑员工功能  
    private void editEmployee(int employeeId) {  
  
    }  
  
    // 删除员工功能  
    private void deleteEmployee(int employeeId) {  
    }  
  
    // 根据员工ID获取员工所在行  
    private int findEmployeeRowById(int employeeId) {  
        for (int i = 0; i < tableModel.getRowCount(); i++) {  
            if (tableModel.getValueAt(i, 0).equals(employeeId)) {  
                return i;  
            }  
        }  
        return -1;  
    }  
  
}
```

## 创建两个类
### 1.创建员工类
```java
package com.zyz.bean;  
  
import lombok.AllArgsConstructor;  
import lombok.Data;  
import lombok.NoArgsConstructor;  
  
@Data  
@AllArgsConstructor  
@NoArgsConstructor  
public class Employee {  
    //员工信息包括员工ID、姓名、性别、年龄、职务、部门、入职时间  
    private int id;  
    private String name;  
    private String gender;  
    private int age;  
    private String job;  
    private String department;  
    private String entryTime;  
}
```
### 2.创建用户类
```java
package com.zyz.bean;  
  
import lombok.AllArgsConstructor;  
import lombok.Data;  
import lombok.NoArgsConstructor;  
  
@Data  
@NoArgsConstructor  
@AllArgsConstructor  
public class User {  
    //用户名、昵称、密码  
    private String username;  
    private String nickname;  
    private String password;  
}
```

## 功能实现
### 1.注册功能实现

在loginui里创建一个静态类变量，存储用户的登录信息。因为没学数据库，先这样存。
```java
public class LoginUI extends JFrame {  
    //静态ArrayList，存储用户的登录信息，类是User  
    private static ArrayList<User> users = new ArrayList<>();
```

创建一个注册界面的ui，让用户填写用户名、昵称、密码。
```java
package com.zyz.ui;  
  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
public class RegisterUI extends JFrame {  
    // 构造函数  
    public RegisterUI() {  
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
                    // 这里可以添加注册逻辑  
                    JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);  
                }  
            }  
        });  
  
        // 将面板添加到窗口  
        add(panel);  
  
        // 窗口可见  
        setVisible(true);  
    }  
  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
                new RegisterUI();  
            }  
        });  
    }  
}
```

将注册界面用户输入的信息，封装成一个用户类对象，添加到loginUi的静态变量中。
为了实现这个功能，首先做了修改，让loginui实现actionlistener的接口。
将loginui对象传递给Registerui，能够接收到数据。
已经实现了注册功能。
### 2.登录功能实现
让gpt根据提示完善登录功能：
//判断用户名是否在集合中，不在则输出用户名不存在。在的话判断密码是否正确，不正确则输出密码错误，正确则跳转到EmplyeeInfoUI界面。
```java
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
                new EmployeeInfoUI();  // 假设你有一个 EmployeeInfoUI 界面  
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
```

### 3.在员工管理界面欢迎用户
在员工管理界面的标题头的地方，写上，用户名，上、中、下午好。
```java
public EmployeeInfoUI(String usernickname) {  
    // 设置窗口标题和大小  
    //获得当前时间，判断上午中午下午晚上  
    String time = TimeofDay();  
    setTitle(time + "好,"+ usernickname+"!,欢迎使用员工管理系统");
```
```java
private String TimeofDay() {  
    // 获取当前时间  
    Date now = new Date();  
  
    // 获取当前小时数  
    int hour = now.getHours();  
  
    // 判断时间段并生成对应的字符串  
    String timeOfDay;  
    if (hour >= 0 && hour < 6) {  
        timeOfDay = "凌晨";  
    } else if (hour >= 6 && hour < 12) {  
        timeOfDay = "上午";  
    } else if (hour >= 12 && hour < 18) {  
        timeOfDay = "下午";  
    } else {  
        timeOfDay = "晚上";  
    }  
    return  timeOfDay;  
}
```

### 4.实现员工管理界面员工信息添加功能
- 需要制作一个新的UI界面，让用户添加员工信息。
```java
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
  
    public AddEmployeeUI() {  
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
  
    // 运行该界面  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
                new AddEmployeeUI();  
            }  
        });  
    }  
}
```

- 修改，实现添加用户信息的功能。同样，因为没有学过数据库，先将员工信息保存到employeeui的静态变量中。
  这里用到一个技巧
  ![[Pasted image 20250108163048.png]]
	在EmployeeInfo界面添加以下函数，每次更改信息后运行，界面展示最新信息。
```java
public void updateEmployeeTable() {  
    // 清空表格  
    tableModel.setRowCount(0);  
  
    // 重新填充表格数据  
    for (Employee employee : employees) {  
        tableModel.addRow(new Object[]{  
                employee.getId(),  
                employee.getName(),  
                employee.getGender(),  
                employee.getAge(),  
                employee.getJob(),  
                employee.getDepartment(),  
                employee.getEntryTime()  
        });  
    }  
}
```
### 5.实现员工删除功能

### 6.实现员工编辑功能
```java
// 编辑菜单项监听  
editMenuItem.addActionListener(new ActionListener() {  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        int selectedRow = employeeTable.getSelectedRow();  
        int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);  
        //通过id号拿到员工信息  
        Employee selectedemployee = null;  
        for (Employee emp : employees) {  
            if (emp.getId() == employeeId) {  
                selectedemployee = emp;  
                break;  
            }  
        }  
        new EditEmployeeUI(EmployeeInfoUI.this, selectedemployee);  
    }  
});
```
这里直接将选中的员工对象，送给编辑界面进行修改。对象只有一个存储空间，所以修改的内容能保持一致性。
### 7.实现员工搜索功能
```java
searchButton.addActionListener(new ActionListener() {  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        //根据员工的姓名搜索，定位到表格的那一行  
        String searchText = searchField.getText();  
        for (int i = 0; i < employees.size(); i++) {  
            Employee employee = employees.get(i);  
            if (employee.getName().equals(searchText)) {  
                int row = findEmployeeRowById(employee.getId());  
                if (row != -1) {  
                    employeeTable.setRowSelectionInterval(row, row);  
                    break;  
                }  
            }  
        }  
    }  
});
```


这个项目的所有内容就完成了。运行APP中的main即可运行该程序。

