package com.shagnguigu.pojo.beanUtilCopyBean;

import lombok.Data;

import java.io.Serializable;

/**
 * 深拷贝需要继承 cloneable 接口
 *
 * Created by wuyufan on 2022/12/29.
 */
@Data
public class Bean1 implements Cloneable, Serializable {
    private int a1;
    private Integer b1;
    private Bean2 inner;

    @Override
    public Bean1 clone() {
        try {
            Bean1 bean1 = (Bean1) super.clone();
            bean1.setInner(inner.clone());
            return bean1;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
