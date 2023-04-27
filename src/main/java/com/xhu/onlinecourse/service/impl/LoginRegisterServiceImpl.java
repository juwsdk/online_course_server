package com.xhu.onlinecourse.service.impl;

import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.mapper.AdmMapper;
import com.xhu.onlinecourse.mapper.StudentMapper;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import com.xhu.onlinecourse.service.LoginRegisterService;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {
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
            System.out.println(student);
            try {
                loginMap.put(student.getStudentId(),student.getStudentPassword());
            } catch (Exception e) {
                throw new UnknownAccountException();
            }
        }else if(userType.equals("teacher")){
            Teacher teacher = teacherMapper.teacherOne(userId);
            loginMap.put(teacher.getTeacherId(),teacher.getTeacherPassword());
        }else if(userType.equals("admin")){
            Adm adm = admMapper.getAdmById(userId);
            loginMap.put(adm.getAdmId(),adm.getAdmPassword());
        }
        System.out.println(loginMap);
        return loginMap;
    }

    @Override
    public Integer register(Object obj,String userType) {
        System.err.println(obj);
        System.err.println(userType);
        if(userType.equals("student")){
            Student student= (Student) obj;
            if(studentMapper.studentInsert(student)==1)
                return Math.toIntExact(studentMapper.studentNumber(student));
            else return 0;
        }else if(userType.equals("teacher")){
            Teacher teacher= (Teacher) obj;
            if(teacherMapper.teacherInsert(teacher)==1)
                return Math.toIntExact(teacherMapper.teacherNumber(teacher));
            else return 0;
        }
        return 1;
    }

    @Override
    public Integer getAdminType(Long adminId) {
        return admMapper.getAdmById(adminId).getAdmAuthoritylevels();
    }
}
