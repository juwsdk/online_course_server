package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.entity.vo.admSta.StudentStaVo;
import com.xhu.onlinecourse.entity.vo.admSta.TeacherStaVo;

import java.util.List;

public interface AdmService {
    //查看管理员列表
    PageInfo<Adm> admList(int pageNum, int pageSize, String columnName, String value);

    //获得管理员信息
    Adm getAdmById(Long admId);

    //增加管理员
    Integer admInsert(Adm adm);

    //删除管理员
    Integer admDelete(Adm adm);

    //更新管理员信息
    Integer admUpdate(Adm adm);

    //统计教师课程
    PageInfo<TeacherStaVo> teacherSta(int pageNum, int pageSize, String columnName, String value);

    //根据id统计教师课程
    List<String> teacherStaById(Long teacherId);

    //统计学生课程
    PageInfo<StudentStaVo> studentSta(int pageNum, int pageSize, String columName, String value);

    //根据id统计学生课程
    List<String> studentStaById(Long studentId);
}
