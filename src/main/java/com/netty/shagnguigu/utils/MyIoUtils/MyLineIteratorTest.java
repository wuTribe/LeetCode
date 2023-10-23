package com.netty.shagnguigu.utils.MyIoUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class MyLineIteratorTest {
    private String basePath = null;

    @Before
    public void before() {
        basePath = Paths.get(System.getProperty("user.dir"), "file").toString();
    }

    // 行迭代器 - 逐行读取
    @Test
    public void lineIterator() throws IOException {
        File file = Paths.get(basePath, "a.txt").toFile();
        LineIterator li = FileUtils.lineIterator(file);
        while (li.hasNext()) {
            System.out.println(li.nextLine());
        }
        // 这个方法被弃用了
        // LineIterator.closeQuietly(li);
        li.close();
    }
}
