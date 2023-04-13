package com.xhu.onlinecourse.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
学生最近三周打卡情况
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendanceVo {
    private Long studentId;
    private Integer firstWeek;
    private Integer secondWeek;
    private Integer thirdWeek;
}
