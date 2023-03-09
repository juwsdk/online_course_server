package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 学生课程作业提交表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseHomeworkSubmit {
    private Long courseHomeworkSubmitId;
    private Long courseHomeworkId;
    private Long studentId;
    private Date courseHomeworkSubmitDate;
}
