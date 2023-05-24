package com.xhu.onlinecourse.entity.vo.admSta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentStaVo {
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
}
