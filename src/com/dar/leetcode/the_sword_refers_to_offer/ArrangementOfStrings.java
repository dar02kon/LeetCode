package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.*;

/**
 * @author :wx
 * @description : 剑指 Offer 38. 字符串的排列 https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/?favorite=xb9nqhhg
 * @create :2022-12-07 08:41:00
 */
public class ArrangementOfStrings {

    public static void main(String[] args) {
        String[] abcs = new ArrangementOfStrings().permutation("abc");
        System.out.println(Arrays.toString(abcs));

    }


    boolean[] visited;
    StringBuilder record = new StringBuilder();
    List<String> list = new ArrayList<>();

    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        char[] array = s.toCharArray();
        Arrays.sort(array);
        record(array);
        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    public void record(char[] s) {
        if (record.length() == s.length) {
            list.add(new String(record));
        }
        for (int i = 0; i < s.length; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && s[i - 1] == s[i])) {
                continue;
            }
            record.append(s[i]);
            visited[i] = true;
            record(s);
            record.deleteCharAt(record.length() - 1);
            visited[i] = false;
        }
    }

    List<String> list2 = new ArrayList<>();

    public String[] permutation2(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);
        do {
            list2.add(new String(array));
        } while (next(array));
        String[] result = new String[list2.size()];
        return list2.toArray(result);
    }

    public boolean next(char[] array) {
        int left = array.length - 2;
        while (left >= 0 && array[left] >= array[left + 1]) {
            left--;
        }
        if (left < 0) {
            return false;
        }
        int right = array.length - 1;
        while (right >= 0 && array[right] <= array[left]) {
            right--;
        }
        if (right >= array.length) {
            return false;
        }

        char target = array[left];
        array[left] = array[right];
        array[right] = target;
        left = left + 1;
        right = array.length - 1;
        while (left < right) {
            target = array[left];
            array[left] = array[right];
            array[right] = target;
            left++;
            right--;
        }
        return true;
    }


}
