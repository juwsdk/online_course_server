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
    private Long courseAttId;
    private Long studentId;
    private Long courseId;
    private Date courseAttDate;
}
