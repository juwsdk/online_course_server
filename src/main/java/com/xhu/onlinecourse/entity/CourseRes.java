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
    protected Long courseResId;
    protected String resVideoName;
    protected String resFileName;
    protected Long courseId;
    protected Long resBlues;
}
