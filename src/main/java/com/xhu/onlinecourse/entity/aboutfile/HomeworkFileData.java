package com.xhu.onlinecourse.entity.aboutfile;

import com.xhu.onlinecourse.entity.CourseHomework;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
/*
继承homework，获取前端上传的文件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkFileData extends CourseHomework {
    private MultipartFile fileRaw;
    private Long teacherId;
}
