package com.xhu.onlinecourse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    protected Date courseHomeworkDate;
    protected String courseHomeworkRes;
    protected String courseHomeworkDescription;

}
