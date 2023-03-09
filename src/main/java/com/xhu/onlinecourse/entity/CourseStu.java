package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生选课表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStu {
    private Long courseStudentId;
    private Long courseId;
    private Long studentId;
}
