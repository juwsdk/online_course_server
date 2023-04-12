package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 打卡表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAttendance {
    protected Long courseAttId;
    protected Long studentId;
    protected Long courseId;
    protected Date courseAttDate;
}
