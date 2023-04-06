package com.xhu.onlinecourse.entity.aboutfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private String fileName;
    private MultipartFile fileRaw;
    private Integer episode;
    private String alias;
}
