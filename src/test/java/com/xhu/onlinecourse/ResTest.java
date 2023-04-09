package com.xhu.onlinecourse;

import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.mapper.CourseMapper;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@SpringBootTest
public class ResTest {
    @Autowired
    CourseMapper courseMapper;

    @Test
    public void getRes() throws FileNotFoundException, UnsupportedEncodingException {
        String property = System.getProperty("user.dir");
        String bashPath=property+"\\src\\main\\resources\\static";
        String srcPath="\\6120101\\1\\res";
        String path=bashPath+srcPath;
        Long courseId=1L;
        insertFile(path,courseId);
    }
    @Transactional
    public  void insertFile(String srcPath,Long courseId) {
        File file = new File(srcPath);
        System.out.println(file);
        String[] fileList = file.list();
        CourseRes courseRes=new CourseRes();
        int i=0;
        for(String fileName:fileList){
            ++i;
            courseRes.setResVideoName(fileName.replace(".mp4",""));
            courseRes.setResFileName(fileName);
            courseRes.setCourseId(courseId);
            courseRes.setResBlues(Integer.toUnsignedLong(i));
            System.out.println(courseRes);
            courseMapper.addNewCourseRes(courseRes);
        }
    }

}
