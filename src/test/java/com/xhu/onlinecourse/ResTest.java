package com.xhu.onlinecourse;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class ResTest {

    @Test
    public void getRes() throws FileNotFoundException {
//        String property = System.getProperty("user.dir");
//        System.out.println(property);
        String path = new ClassPathResource("/static").getPath();
        File file=new File(path);
        System.out.println(file);
    }
}
