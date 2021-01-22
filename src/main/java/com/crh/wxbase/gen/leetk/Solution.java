package com.crh.wxbase.gen.leetk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-21 16:58
 */
public class Solution {

    public List<Integer> addToArrayForm(int[] arr, int k) {
        StringBuilder str = new StringBuilder();
        for(int a : arr){
            str.append(a);
        }
        List<Integer> integers = new ArrayList<>();
        String[] strs = ((Integer.parseInt(str.toString()) + k) + "").split("");
        for(int j=0;j<strs.length;j++){
            integers.add(Integer.valueOf(strs[j]));
        }
        return integers;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().addToArrayForm(new int[]{9,9,9,9,9,9,9,9,9,9}, 56));
    }
}
