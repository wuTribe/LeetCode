package com.heima.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestFilesCopy {
    public static void main(String[] args) throws IOException {
        String source = "";
        String target = "";

        Files.walk(Paths.get(source)).forEach(e -> {
            try {
                String targetName = e.toString().replace(source, target);
                if (Files.isDirectory(e)) {
                    Files.createDirectory(Paths.get(targetName));
                } else if (Files.isRegularFile(e)) {
                    Files.copy(e, Paths.get(targetName));
                }
            } catch (Exception ex) {
                log.debug("", ex);
            }
        });
    }
}
