package com.crh.wxbase.gen.leetk;

/**
 * @author rory.chen
 * @date 2021-01-21 16:58
 */
public class Solution {
    public int reverse(int x) {
        String reverse = new StringBuilder(x < 0 ? String.valueOf(x).substring(1) : String.valueOf(x)).reverse().toString();
        try {
            Integer value = Integer.valueOf(reverse);
            return x < 0 ? -value : value;
        } catch (NumberFormatException e){
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(-1234567899));
    }
}
