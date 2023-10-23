package com.netty.shagnguigu.utils.MyIoUtils;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class MyFileUtilsTest {
    private String basePath = null;

    @Before
    public void before() {
        basePath = System.getProperty("user.dir") + "\\file\\";
    }

    // 文件拷贝
    @Test
    public void copyTest() throws IOException {
        File srcFile = new File(basePath + "a.txt");
        File destFile = new File(basePath + "b.txt");

        FileUtils.copyFile(srcFile, destFile);
    }

    // 删除一个文件 or 文件夹（当文件夹内部有文件时，也可以删除）
    @Test
    public void deleteTest() throws IOException {
        File destFile = new File(basePath + "b.txt");
        FileUtils.forceDelete(destFile);
    }

    // 创建一个目录
    @Test
    public void forceMkdir() throws IOException {
        File dir = new File(basePath + "aaaaab");
        FileUtils.forceMkdir(dir);
    }

    // 比较文件内容
    @Test
    public void contentEquals() throws IOException {
        File srcFile = new File(basePath + "a.txt");
        File destFile = new File(basePath + "b.txt");
        boolean result = FileUtils.contentEquals(srcFile, destFile);
        System.out.println(result);
    }

    // 移动文件 or 移动到目录 or 重命名
    @Test
    public void moveFile() throws IOException {
        File destFile = new File(basePath + "b.txt");
        File moveFile = new File(basePath + "aaaa\\c.txt");
        FileUtils.moveFile(destFile, moveFile);
    }

    // 移动文件到目录
    // moveToDirectory 移动到目录，最后一个是目录名
    // moveFile 也可以移动到目录，但是最后一个填的是文件名
    @Test
    public void moveToDirectory() throws IOException {
        File destFile = new File(basePath + "b.txt");
        File moveFile = new File(basePath + "bbbbb\\c.txt");
        FileUtils.moveToDirectory(destFile, moveFile, true);
    }

    // 读取文件
    @Test
    public void read() throws IOException {
        File srcFile = new File(basePath + "a.txt");
        // 一次读取全部
        String content = FileUtils.readFileToString(srcFile, StandardCharsets.UTF_8);
        System.out.println(content);
        System.out.println("=====================");
        // 分行读
        List<String> strings = FileUtils.readLines(srcFile, StandardCharsets.UTF_8);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    // 写入文件内容
    @Test
    public void writeStringToFile() throws IOException {
        File srcFile = new File(basePath + "a.txt");
        FileUtils.writeStringToFile(srcFile, "\n\n\nsTTTTtttt", StandardCharsets.UTF_8, true);
    }
}
