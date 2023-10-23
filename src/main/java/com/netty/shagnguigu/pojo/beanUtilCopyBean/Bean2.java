package com.netty.shagnguigu.pojo.beanUtilCopyBean;

import lombok.Data;

import java.io.Serializable;

/**
 * 深拷贝需要继承 cloneable 接口
 *
 * Created by wuyufan on 2022/12/29.
 */
@Data
public class Bean2 implements Cloneable, Serializable {
    private int a2;
    private Integer b2;

    @Override
    public Bean2 clone() {
        try {
            return (Bean2) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
