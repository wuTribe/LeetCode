package com.leetcode.q729;

import java.util.Comparator;
import java.util.TreeSet;

class MyCalendar {
    TreeSet<int[]> booked;

    public MyCalendar() {
        booked = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[]{start, end});
            return true;
        }
        int[] tmp = {end, 0};

        // [start1, end1] <= [new_start, new_end] <= [start_2, end_2]

        // ceiling 方法查找集合中第一个开始时间大于或等于 tmp[0]（即 end）的时间段 arr
        // 这个操作意味着寻找下一个开始时间大于等于 end 的预约，这样可以找到不与新预约冲突的时间段。
        int[] arr = booked.ceiling(tmp);
        // 查找结束时间小于 待预约时间的结束时间
        // booked.lower(tmp) 查找集合中开始时间比新预约的结束时间 end 小的最后一个时间段。如果找不到这样的时间段，说明所有时间段都比新预约的结束时间 end 大。
        if (arr == booked.first() || booked.lower(tmp)[1] <= start) {
            booked.add(new int[]{start, end});
            return true;
        }
        return false;
    }
}
