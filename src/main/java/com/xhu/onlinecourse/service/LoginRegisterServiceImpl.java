package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.mapper.AdmMapper;
import com.xhu.onlinecourse.mapper.StudentMapper;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService{
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdmMapper  admMapper;
    @Override
    public Map<Long, String> getUserInfo(Long userId,String userType) {
        HashMap<Long, String> loginMap = new HashMap<>();
        if(userType.equals("student")){
            Student student = studentMapper.studentById(userId);
            loginMap.put(student.getStudentId(),student.getStudentPassword());
        }else if(userType.equals("teacher")){
            Teacher teacher = teacherMapper.teacherOne(userId);
            loginMap.put(teacher.getTeacherId(),teacher.getTeacherPassword());
        }else if(userType.equals("admin")){
            Adm adm = admMapper.getAdmById(userId);
            loginMap.put(adm.getAdmId(),adm.getAdmPassword());
        }
        return loginMap;
    }
}
