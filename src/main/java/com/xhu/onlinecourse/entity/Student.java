package com.xhu.onlinecourse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date studentBirthday;
    private String studentPhonenum;
    private String studentEmail;
    private String studentAddress;
    private Integer studentFirstlogin;
}
