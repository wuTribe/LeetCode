package com.netty.shagnguigu.utils;

import com.google.common.collect.Maps;
import com.netty.shagnguigu.pojo.beanUtilCopyBean.Bean1;
import com.netty.shagnguigu.pojo.beanUtilCopyBean.Bean2;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class MyBeanUtilsTest {
    // 对象克隆
    // Apache Commons 类库可以对 Java bean 进行各种操作，比如复制对象，属性等等：https://blog.csdn.net/weixin_41563161/article/details/117046259
    // 需要导入 commons-beanutils jar：https://commons.apache.org/proper/commons-beanutils/
    @Test
    public void beanUtilsCopyTest() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Bean1 bean1 = new Bean1();
        Bean2 bean2 = new Bean2();

        bean2.setA2(111);
        bean2.setB2(222);

        bean1.setA1(123);
        bean1.setB1(345);
        bean1.setInner(bean2);

        // 对象克隆
        Bean1 o = (Bean1) BeanUtils.cloneBean(bean1);
        // true 浅拷贝
        // 深拷贝需要继承 cloneable 接口
        System.out.println("o.getInner() == bean1.getInner() = " + (o.getInner() == bean1.getInner()));
    }
    // 可能涉及深拷贝浅拷贝问题：https://blog.csdn.net/Romantic_321/article/details/119186506
    // -- MapStrust 可能项目不会导入，而常用的 Spring BeanUtils 属于浅拷贝
    @Test
    public void beanUtilsCopyTest2() {
        Bean1 bean1 = new Bean1();
        Bean2 bean2 = new Bean2();

        bean2.setA2(111);
        bean2.setB2(222);

        bean1.setA1(123);
        bean1.setB1(345);
        bean1.setInner(bean2);

        // 对象克隆
        Bean1 target = new Bean1();
        org.springframework.beans.BeanUtils.copyProperties(bean1, target);
        // true 浅拷贝
        System.out.println("(bean1.getInner() == target.getInner()) = " + (bean1.getInner() == target.getInner()));
    }

    // 深拷贝需要继承 cloneable 接口
    // 或者使用序列化实现深拷贝
    @Test
    public void beanUtilsCopyTest3() {
        Bean1 bean1 = new Bean1();
        Bean2 bean2 = new Bean2();

        bean2.setA2(111);
        bean2.setB2(222);

        bean1.setA1(123);
        bean1.setB1(345);
        bean1.setInner(bean2);

        Bean1 target = (Bean1) SerializationUtils.clone(bean1);
        // false 深拷贝
        System.out.println("(bean1.getInner() == target.getInner()) = " + (bean1.getInner() == target.getInner()));
    }

    // 将 map 拷贝到 bean 中（key 相同）
    @Test
    public void beanUtilsCopyTest4() throws InvocationTargetException, IllegalAccessException {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("a1", 1234);
        map.put("b1", 1234);
        Bean1 bean1 = new Bean1();

        BeanUtils.populate(bean1, map);
        System.out.println(bean1);

        Bean1 bean11 = new Bean1();
        org.springframework.beans.BeanUtils.copyProperties(map, bean11);
        System.out.println(bean11);
    }
}
