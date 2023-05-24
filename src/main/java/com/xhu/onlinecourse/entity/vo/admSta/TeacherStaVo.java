package com.xhu.onlinecourse.entity.vo.admSta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherStaVo {
    private Long teacherId;
    private String teacherName;
    private Long courseId;
    private String courseName;
}
