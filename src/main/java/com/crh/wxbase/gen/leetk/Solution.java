package com.crh.wxbase.gen.leetk;

/**
 * @author rory.chen
 * @date 2021-01-21 16:58
 */
public class Solution {

    public Boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        Integer oldValue = x;
        int len = oldValue.toString().length();
        // len / 2 ：比如是1234321，7位数，那7/2=3，循环三次，从后往前取三个数，
        // 从前往后取三个数比较，中间第4个数不比较，直接跳过
        for (int i = 0; i < len / 2; i++) {
            //从后往前
            int last = oldValue % 10;
            //从前往后  ——>  Math.pow是次方的意思
            int first = (int) (x / Math.pow(10, len - (i + 1))) % 10;
            if (first != last) {
                return false;
            }
            // 如果相同则 剔除最后一位数字，比如：123 -> 12
            // 这样 int last = oldValue % 10 这一次取 3 下一次就取的是2了
            oldValue /= 10;
        }
        return true;
    }

}
