package com.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestFileWalkFileTree {
    static Path path = Paths.get("C:\\Program Files\\Java\\jdk1.8.0_202");

    public static void main(String[] args) throws IOException {
        // 删除文件夹
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file); // 删里面的文件
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir); // 删文件夹
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    public void m1() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                log.debug(" ===> " + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                log.debug("" + file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        log.debug("dir count:" + dirCount);
        log.debug("file count:" + fileCount);
    }

    public void m2() throws IOException {
        AtomicInteger jarCount = new AtomicInteger();

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jar")) {
                    log.debug("" + file);
                    jarCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        log.debug("jar count:" + jarCount);
    }
}
