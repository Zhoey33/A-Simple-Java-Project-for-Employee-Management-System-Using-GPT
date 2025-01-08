package com.zyz.ui;

import com.zyz.bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeInfoUI extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private ArrayList<Employee> employees = new ArrayList<>();

    // 构造函数
    public EmployeeInfoUI(String usernickname) {
        // 设置窗口标题和大小
        //获得当前时间，判断上午中午下午晚上
        String time = TimeofDay();
        setTitle(time + "好,"+ usernickname+"!欢迎使用员工管理系统");
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

        // 添加员工按钮监听
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeUI(EmployeeInfoUI.this);
            }
        });

        // 将组件加入顶部面板
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addButton);

        // 表格模型
        tableModel = new DefaultTableModel(new Object[]{"员工ID", "姓名", "性别", "年龄", "职务", "部门", "入职时间"}, 0);
        employeeTable = new JTable(tableModel);

        employeeTable.setDefaultEditor(Object.class, null);

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

        // 删除菜单项监听
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //拿到这一行的员工ID
                int employeeId = (int) employeeTable.getValueAt(employeeTable.getSelectedRow(), 0);
                deleteEmployee(employeeId);
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

    // 删除员工功能
    private void deleteEmployee(int employeeId) {
        //将该员工ID的员工从employees中删除
        employees.removeIf(employee -> employee.getId() == employeeId);
        tableModel.removeRow(findEmployeeRowById(employeeId));
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

    void addEmployee(Employee employee) {
        // 添加员工到列表
        employees.add(employee);

        // 更新表格
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

    //添加 main 方法
    public static void main(String[] args) {
        new EmployeeInfoUI("超人探探");
    }
}
