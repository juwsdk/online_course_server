package com.xhu.onlinecourse.entity.aboutfile;

import com.xhu.onlinecourse.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
/*
courseFile继承Course，多了一个file属性获取前端的文件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFile extends Course {
    private MultipartFile fileRaw;
}
