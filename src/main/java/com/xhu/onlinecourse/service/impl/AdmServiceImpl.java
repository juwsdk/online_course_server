package com.xhu.onlinecourse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.entity.vo.admSta.StudentStaVo;
import com.xhu.onlinecourse.entity.vo.admSta.TeacherStaVo;
import com.xhu.onlinecourse.mapper.AdmMapper;
import com.xhu.onlinecourse.service.AdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmServiceImpl implements AdmService {

    @Autowired
    private AdmMapper admMapper;

    @Override//查看管理员列表
    public PageInfo<Adm> admList(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<Adm> admList = admMapper.getAdmList(columnName, value);
        return new PageInfo<>(admList);
    }

    @Override//通过id获取管理员信息
    public Adm getAdmById(Long admId) {
        return admMapper.getAdmById(admId);
    }

    @Override//增加管理员
    public Integer admInsert(Adm adm) {
        return admMapper.admInsert(adm);
    }

    @Override//删除管理员
    public Integer admDelete(Adm adm) {
        return admMapper.admDelete(adm);
    }

    @Override//更新管理员
    public Integer admUpdate(Adm adm) {
        return admMapper.admUpdate(adm);
    }

    @Override//统计教师信息
    public PageInfo<TeacherStaVo> teacherSta(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherStaVo> teacherStaVoList = admMapper.teacherSta(columnName, value);
        return new PageInfo<>(teacherStaVoList);
    }

    @Override//根据id统计教师信息
    public List<String> teacherStaById(Long teacherId) {
        return admMapper.teacherStaById(teacherId);
    }

    @Override//统计学生信息
    public PageInfo<StudentStaVo> studentSta(int pageNum, int pageSize, String columName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentStaVo> studentStaVoList = admMapper.studentSta(columName, value);
        return new PageInfo<>(studentStaVoList);
    }

    @Override//根据id统计学生信息
    public List<String> studentStaById(Long studentId) {
        return admMapper.studentStaById(studentId);
    }

}
