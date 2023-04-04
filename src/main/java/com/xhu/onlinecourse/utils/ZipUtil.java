package com.xhu.onlinecourse.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static final int BUFFER_SIZE = 1024;

    /**
     * 压缩文件
     *
     * @param srcFile   待压缩文件或文件夹
     * @param destFile  压缩后的zip文件
     * @throws IOException
     */
    public static void zip(File srcFile, File destFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(destFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            // 递归压缩文件夹
            compress(srcFile, zos, "");
        }
    }

    private static void compress(File file, ZipOutputStream zos, String basePath) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                // 空文件夹
                ZipEntry entry = new ZipEntry(basePath + file.getName() + "/");
                zos.putNextEntry(entry);
                zos.closeEntry();
            } else {
                for (File f : files) {
                    // 递归压缩子文件夹
                    compress(f, zos, basePath + file.getName() + "/");
                }
            }
        } else {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                ZipEntry entry = new ZipEntry(basePath + file.getName());
                zos.putNextEntry(entry);
                byte[] data = new byte[BUFFER_SIZE];
                int len;
                while ((len = bis.read(data)) != -1) {
                    zos.write(data, 0, len);
                }
                zos.closeEntry();
            }
        }
    }

    /**
     * 解压缩文件
     *
     * @param zipFile   压缩文件
     * @param destDir   解压缩目录
     * @throws IOException
     */
    public static void unzip(File zipFile, File destDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(zipFile);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    // 创建空文件夹
                    file.mkdirs();
                } else {
                    // 创建文件父目录
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
                        byte[] data = new byte[BUFFER_SIZE];
                        int len;
                        while ((len = zis.read(data)) != -1) {
                            bos.write(data, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
}