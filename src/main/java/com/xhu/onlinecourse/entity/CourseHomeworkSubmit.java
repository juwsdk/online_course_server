package com.xhu.onlinecourse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    protected Long courseHomeworkSubmitId;
    protected Long courseHomeworkId;
    protected Long studentId;
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    protected Date courseHomeworkSubmitDate;
    protected String courseHomeworkRes;
}
