package com.xhu.onlinecourse.entity.aboutfile;

import com.xhu.onlinecourse.entity.CourseHomeworkSubmit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkSubmitFileData extends CourseHomeworkSubmit {
    private MultipartFile fileRaw;
    private Long teacherId;
    private Long courseId;
}
