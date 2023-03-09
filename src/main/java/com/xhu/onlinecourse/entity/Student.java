package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 学生表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long studentId;
    private String studentName;
    private String studentPassword;
    private String studentGender;
    private Date studentBirthday;
    private String studentPhonenum;
    private String studentEmail;
    private String studentAddress;
    private int studentFirstlogin;
}
