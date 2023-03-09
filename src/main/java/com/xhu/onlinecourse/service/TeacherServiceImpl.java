package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public PageInfo<Teacher> teacherList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teacherList = teacherMapper.teacherList();
        return new PageInfo<>(teacherList);
    }
}
