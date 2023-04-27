package com.xhu.onlinecourse.entity.aboutfile;

import com.xhu.onlinecourse.entity.CourseRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
/*
fileData对象继承CourseRes，获取前端上传的文件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData extends CourseRes {
    private MultipartFile fileRaw;
    private Long teacherId;
}
