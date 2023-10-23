package com.netty.shagnguigu.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class MyCodecTest {
    // commons-codec.jar
    // 里面有一些编码实现，比如 base64，Hex，MD5 编码
    @Test
    public void decodeTest() {
        // 编码
        Base64 base64 = new Base64();
        String encode = base64.encodeToString("Base64Encode".getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        // 解码
        String deCode = new String(Base64.decodeBase64(encode));
        System.out.println(deCode);
    }

    @Test
    public void hex() {
        System.out.println(Hex.encodeHexString("Base64Encode".getBytes(StandardCharsets.UTF_8)));
    }
}
