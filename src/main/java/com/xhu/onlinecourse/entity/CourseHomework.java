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
    protected Long courseHomeworkId;
    protected String courseHomeworkName;
    protected Long courseId;
    protected Date courseHomeworkDate;
    protected String courseHomeworkRes;
    protected String courseHomeworkDescription;

}
