package com.swordpointing2offer.lcr187;

public class Solution {

    /*
    思路：使用数学方法(先举例)
        你要知道最后的结果是3，带着结果去看问题

    第一次，【0, 1, 2, 3, 4】，本轮要踢出2                                  看3
    (下一轮开始从3计数，为了方便读者看出规律，将开始计数的那一位移到开头)
    第二次，【3, 4, 0, 1】，本轮要踢出0                                     看1
    第三次，【1, 3, 4】，本轮要踢出4                                        看1
    第四次，【1, 3】 本轮要踢出1                                            看3
    第五次，【3】
    最后返回3

    我们要使用的数学方法，就是从结果0号位置，反推最开始在哪
    你从第二次，向上看第一次
    你会发现，原来3在0的位置
            现在，3在(0 + 3) % 5
                    => +3 回到上次的位置
                    => %5 防止数组溢出，并且数组本来就是循环数组

    f(n) = ( f(n - 1) + m ) % n
    解释意思：
        f(n) 表示上一次
        f(n - 1) 表示这次，因为我们要从这次回推上一次
        m 表示隔几个
        n表示上一次的数组长度

 */
    // 11 ==== 3
    // 1、2、3、4、5、6、7、8、9、10、11
    // 1、2、 、4、5、6、7、8、9、10、11 === 3
    // 1、2、 、4、5、 、7、8、9、10、11 === 6
    // 1、2、 、4、5、 、7、8、 、10、11 === 9
    //  、2、 、4、5、 、7、8、 、10、11 === 1
    //  、2、 、4、 、 、7、8、 、10、11 === 5
    //  、2、 、4、 、 、7、8、 、  、11 === 10
    //  、2、 、 、 、 、7、8、 、  、11 === 4
    //  、2、 、 、 、 、7、8、 、  、   === 11
    //  、 、 、 、 、 、7、8、 、  、   === 2
    //  、 、 、 、 、 、 、8、 、  、   === 7
    public int iceBreakingGame(int num, int target) {
        // 起始位置
        int index = 0;
        for (int i = 2; i <= num; i++) {
            index = (index + target) % i;
        }
        return index;
    }
    
    // 0、1、2、3、4、5、6、7
    // 0、1、2、 、4、5、6、7 == 3
    // 0、1、2、 、4、5、6、  == 7
    // 0、1、2、 、 、5、6、  == 4
    // 0、1、2、 、 、5、6、  == 4
    public static void main(String[] args) {
        System.out.println(new Solution().iceBreakingGame(7, 4));
    }
}
