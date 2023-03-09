package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 教师表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Long teacherId;
    private String teacherName;
    private String teacherPassword;
    private String teacherGender;
    private Date teacherBirthday;
    private String teacherPhonenum;
    private String teacherEmail;
    private String teacherAddress;
}
