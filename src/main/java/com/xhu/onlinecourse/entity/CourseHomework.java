package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 教师布置作业表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseHomework {
    private Long courseHomeworkId;
    private String courseHomeworkName;
    private Long courseId;
    private Date courseHomeworkDate;
    private String courseHomeworkRes;
    private String courseHomeworkDescription;

}
