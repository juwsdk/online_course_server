package com.xhu.onlinecourse.entity.aboutfile;

import com.xhu.onlinecourse.entity.CourseHomework;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkFileData extends CourseHomework {
    //courseHomeworkName name
    //courseHomeworkDate date
    //courseHomeworkDescription description
    private MultipartFile fileRaw;
    private Long teacherId;
}
