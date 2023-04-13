package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.vo.StudentAttendanceVo;
import com.xhu.onlinecourse.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<Student> studentList(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentMapper.studentList(columnName, value);
        return new PageInfo<>(studentList);
    }

    @Override
    public Integer studentInsert(Student student) {
        return studentMapper.studentInsert(student);
    }

    @Override
    public Integer studentDelete(Long studentId) {
        return studentMapper.studentDelete(studentId);
    }

    @Override
    public Integer studentUpdate(Student student) {
        return studentMapper.studentUpdate(student);
    }

    @Override///学生作业完成情况与总作业数
    public Map<String, Integer> studentHomeWork(Long studentId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("student", studentMapper.studentHomeWorkIsFinished(studentId));
        map.put("all", studentMapper.studentChooseAllHomeworks(studentId));
        return map;
    }

    @Override//学生提出的问题和总的问题数
    public Map<String, Integer> studentQuestionsNum(Long studentId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("student", studentMapper.studentAskQuestions(studentId));
        map.put("all", studentMapper.allQuestions());
        return map;
    }

    @Override//学生最近三周打卡情况
    public StudentAttendanceVo studentAttendance(Long studentId) {
        return studentMapper.studentAttendance(studentId);
    }

}
