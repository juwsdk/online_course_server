package com.xhu.onlinecourse.entity.vo;

import com.xhu.onlinecourse.entity.CourseAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAttendanceVo extends CourseAttendance {
    private String studentName;
}
