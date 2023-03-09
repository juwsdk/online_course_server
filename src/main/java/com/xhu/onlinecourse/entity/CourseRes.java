package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程资源存放表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRes {
    private Long courseResId;
    private String resVideoName;
    private String resFileName;
    private Long courseId;
}
