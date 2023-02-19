package com.wu.utils.MyIoUtils;

import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class FileFilterTest {
    private String basePath = null;

    @Before
    public void before() {
        basePath = Paths.get(System.getProperty("user.dir"), "file").toString();
    }

    // org.apache.commons.io.filefilter 包
    // 定义了一个合并了 FileFilter 和 FilenameFilter 的接口，除此之外，这个包还提供了一系列可用的 IOFileFilter 的实现类
    // 可用通过他们合并其他的文件过滤器，比如这些文件过滤器可用在列出文件时使用或者使用文件对话框时使用

    // 空内容文件过滤器
    @Test
    public void emptyFileFilter() {
        File dir = new File(basePath);
        String[] files = dir.list(EmptyFileFilter.NOT_EMPTY);
        if (Objects.nonNull(files)) {
            for (String file : files) {
                System.out.println(file);
            }
        }
    }

    // 文件名后缀过滤器
    @Test
    public void suffixFilterFilter() {
        File dir = new File(basePath);
        String[] files = dir.list(new SuffixFileFilter("txt"));
        if (Objects.nonNull(files)) {
            for (String file : files) {
                // file = a.txt
                // file = c.txt
                System.out.println("file = " + file);
            }
        }
    }
}
