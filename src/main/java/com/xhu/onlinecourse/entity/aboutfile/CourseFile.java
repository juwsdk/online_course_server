package com.xhu.onlinecourse.entity.aboutfile;

import com.xhu.onlinecourse.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFile extends Course {
    private MultipartFile fileRaw;
}
