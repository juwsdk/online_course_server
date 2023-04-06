package com.xhu.onlinecourse.entity.aboutfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.File;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeWorkFileData {
    private String name;
    private Date date;
    private File Res;
    private String description;

}
