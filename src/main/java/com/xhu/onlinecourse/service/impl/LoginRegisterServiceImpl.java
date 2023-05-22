package com.xhu.onlinecourse.service.impl;

import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.loginuser.User;
import com.xhu.onlinecourse.mapper.AdmMapper;
import com.xhu.onlinecourse.mapper.StudentMapper;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import com.xhu.onlinecourse.service.LoginRegisterService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdmMapper admMapper;

    @Override
    public Boolean getUserInfo(User user) {
        if (user.getLoginType().equals("student")) {
            Student student = studentMapper.studentById(user.getUserId());
            if (student == null)
                throw new UnknownAccountException();//没有此用户
            if (!student.getStudentPassword().equals(user.getPassword()))
                throw new IncorrectCredentialsException();//密码错误
        } else if (user.getLoginType().equals("teacher")) {
            Teacher teacher = teacherMapper.teacherOne(user.getUserId());
            if (teacher == null)
                throw new UnknownAccountException();
            if (!teacher.getTeacherPassword().equals(user.getPassword()))
                throw new IncorrectCredentialsException();
        } else if (user.getLoginType().equals("admin")) {
            Adm adm = admMapper.getAdmById(user.getUserId());
            if (adm == null)
                throw new UnknownAccountException();
            if (!adm.getAdmPassword().equals(user.getPassword()))
                throw new IncorrectCredentialsException();
        }
        return true;
    }

    @Override
    public Boolean checkUserId(User user) {
        if (user.getLoginType().equals("student")) {
            Student student = studentMapper.studentById(user.getUserId());
            System.out.println(student);
            if (student == null)
                return false;
        } else if (user.getLoginType().equals("teacher")) {
            Teacher teacher = teacherMapper.teacherOne(user.getUserId());
            if (teacher == null)
                return false;
        } else if (user.getLoginType().equals("admin")) {
            Adm adm = admMapper.getAdmById(user.getUserId());
            if (adm == null)
                return false;
        }
        return true;
    }

    @Override
    public Integer register(Object obj, String userType) {
        if (userType.equals("student")) {
            Student student = (Student) obj;
            if (studentMapper.studentInsert(student) == 1)
                return Math.toIntExact(studentMapper.studentNumber(student));
            else return 0;
        } else if (userType.equals("teacher")) {
            Teacher teacher = (Teacher) obj;
            if (teacherMapper.teacherInsert(teacher) == 1)
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
