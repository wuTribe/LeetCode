package com.wu.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wuyufan on 2022/12/29.
 */
public class MyCollectionsTest {
    List<String> list = Lists.newArrayList();
    @Before
    public void before() {
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("a");
    }

    // 排序
    @Test
    public void sort() {
        Collections.sort(list);
        System.out.println("list = " + list);
    }

    // 翻转
    @Test
    public void reverse() {
        Collections.sort(list);
        Collections.reverse(list);
        System.out.println("list = " + list);
    }

    // 随机排序
    @Test
    public void shuffle() {
        Collections.sort(list);
        Collections.shuffle(list);
        System.out.println("list = " + list);
    }

    // 填充
    @Test
    public void fill() {
        Collections.fill(list, "我");
        System.out.println("list = " + list);
    }

    // 数组拷贝，dest source，重复索引会覆盖
    @Test
    public void copy() {
        List<String> copyList = Lists.newArrayList();
        copyList.add("1");
        copyList.add("1");
        Collections.copy(list, copyList);
        System.out.println("copyList = " + list);
    }

    // 找到极值
    @Test
    public void maxMin() {
        // 还可以使用 Comparator 接口比较
        System.out.println("Collections.max(list) = " + Collections.max(list));
        System.out.println("Collections.min(list) = " + Collections.min(list));

        // o1 是前面的对象，o2 是后面的对象
        // 返回负数，说明不需要交换 asc
        // 返回正数，说明需要交换 desc
        System.out.println("Collections.max(list) = " + Collections.max(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(0) - o2.charAt(0);
            }
        }));
        System.out.println("Collections.max(list) = " + Collections.max(list, Comparator.comparingInt(o -> o.charAt(0))));
    }

    // m,n 查找 n 在 m 首次出现的索引
    @Test
    public void indexOfSubList() {
        List<String> findList = Lists.newArrayList();
        findList.add("a");
        System.out.println("list = " + list);
        System.out.println("Collections.indexOfSubList(list, findList) = " + Collections.indexOfSubList(list, findList));
    }

    // m,n 查找 n 在 m 最后最先的位置
    @Test
    public void lastIndexOfSubList() {
        list.add("a");

        List<String> findList = Lists.newArrayList();
        findList.add("a");
        System.out.println("list = " + list);
        System.out.println("Collections.indexOfSubList(list, findList) = " + Collections.lastIndexOfSubList(list, findList));
    }

    // 数组中的元素移动 m 个位置，最后的元素被循环到前面
    // 正数往右移动，负数往左边移动
    // list = [c, d, b, a]
    // list = [d, b, a, c]
    @Test
    public void rotate() {
        System.out.println("list = " + list);
        Collections.rotate(list, 3);
        System.out.println("list = " + list);
    }

    // 交换列表元素指定位置
    @Test
    public void swap() {
        System.out.println("list = " + list);
        Collections.swap(list, 0, 2);
        System.out.println("list = " + list);
    }

    // 查找元素，二分查找是有前提条件的-数组有序：https://blog.csdn.net/weixin_40688217/article/details/108144846
    // 和查找方式有关
    @Test
    public void binarySearch() {
        Collections.sort(list);
        System.out.println("list = " + list);
        System.out.println("Collections.binarySearch = " + Collections.binarySearch(list, "a"));
    }

    // 替换全部
    @Test
    public void replaceAll() {
        Collections.replaceAll(list, "a", "000");
        System.out.println("list = " + list);
    }

    // 两个链表中重叠的部分，CollectionUtils 中的类
    @Test
    public void retainAll() {
        List<String> list1 = Lists.newArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = Lists.newArrayList();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        System.out.println(CollectionUtils.retainAll(list1, list2));
    }
}
