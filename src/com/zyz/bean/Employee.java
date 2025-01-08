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
