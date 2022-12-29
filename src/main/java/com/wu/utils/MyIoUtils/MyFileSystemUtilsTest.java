package com.wu.utils.MyIoUtils;

import org.apache.commons.io.FileSystemUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class MyFileSystemUtilsTest {
    // 获取空闲的磁盘空间
    @Test
    public void freeSpace() throws IOException {
        // 以 k 为单位
        System.out.println(FileSystemUtils.freeSpace("c:\\") / 1024 / 1024);
        System.out.println(FileSystemUtils.freeSpace("d:\\") / 1024 / 1024);
        System.out.println(FileSystemUtils.freeSpace("e:\\") / 1024 / 1024);
        System.out.println("============================================================");
        // 以字节为单位
        System.out.println(FileSystemUtils.freeSpace("c:\\") / 1024 / 1024 / 1024);
        System.out.println(FileSystemUtils.freeSpace("d:\\") / 1024 / 1024 / 1024);
        System.out.println(FileSystemUtils.freeSpace("e:\\") / 1024 / 1024 / 1024);
    }
}
