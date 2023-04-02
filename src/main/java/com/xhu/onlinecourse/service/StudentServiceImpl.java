package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageInfo<Student> studentList(int pageNum, int pageSize,String columnName,String value) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> studentList = studentMapper.studentList(columnName,value);
        return new PageInfo<>(studentList);
    }

    @Override
    public int studentInsert(Student student) {
        return studentMapper.studentInsert(student);
    }

}
