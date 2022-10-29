package com.dar.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :wx
 * @description : 有效的数独 https://leetcode.cn/problems/valid-sudoku/
 * @create :2022-10-29 13:18:00
 */
public class ValidSudoku {

    public static void main(String[] args) {

    }

    /**
     * 但凡动一点脑子也不至于一点脑子都没动
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<>(9);
        Set<Character> set2 = new HashSet<>(9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (set.contains(board[i][j])) {
                        return false;
                    } else {
                        set.add(board[i][j]);
                    }
                }
                if (board[j][i] != '.') {
                    if (set2.contains(board[j][i])) {
                        return false;
                    } else {
                        set2.add(board[j][i]);
                    }
                }
            }
            set.clear();
            set2.clear();
        }
        for (int i = 0; i < 9; i += 3) {
            for (int k = 0; k < 9; k += 3) {
                for (int j = i; j < i + 3; j++) {
                    for (int l = k; l < k + 3; l++) {
                        if (board[j][l] != '.') {
                            if (set.contains(board[j][l])) {
                                return false;
                            } else {
                                set.add(board[j][l]);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 使用数组来标记每一行，每一列，每一个小方块的数字出现情况
     * 数组使用boolean类型，初始化为false
     * 每一行，每一列，每一个小方块的数字出现情况都是9个一组用数组的一行来表示，
     * 位于哪一行由数字下标计算得来，每一行的具体位置根据数字来计算
     * 对于9块小矩阵需要通过它们的下标把它们转换成对应的0-9的行坐标
     * 出现过的数字对应为标记为true，如果出现再次访问该位置则说明出现重复返回false
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        boolean[][] row = new boolean[10][10], column = new boolean[10][10], matrix = new boolean[10][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int num = c - '0';//获取数字
                int index = i / 3 * 3 + j / 3;//计算小矩阵对应行坐标
                if (row[i][num] || column[j][num] || matrix[index][num]) return false;//出现再次访问
                row[i][num] = column[j][num] = matrix[index][num] = true;//在组内第一次出现，标记为false
            }
        }
        return true;
    }

    /**
     * 位运算:
     * 更进一步，我们可以使用一个 int来记录 某行/某列/某个小方块的数值填入情况：使用从低位开始的 [1,9] 位来记录该数值是否已被填入。
     * 例如 (...111000111) 代表数值 [1,3] 和 [7, 9] 均被填入。
     * <<：将二进制数字向左移动，移动几位就在最右侧补多少个0
     * >>：将二进制数字向右移动，移动几位就在最右侧消去多少个比特位，并最左侧填充符号位。
     * |：当相同位上的数字至少有一个为1时，结果为1；否则为0
     *
     * 作者：AC_OIer
     * 链接：https://leetcode.cn/problems/valid-sudoku/solution/gong-shui-san-xie-yi-ti-san-jie-ha-xi-bi-ssxp/
     * @param board
     * @return
     */
    public boolean isValidSudoku3(char[][] board) {
        int[] row = new int[10], col = new int[10], area = new int[10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if ((((row[i] >> u) & 1) == 1) || (((col[j] >> u) & 1) == 1) || (((area[idx] >> u) & 1) == 1)) return false;
                row[i] |= (1 << u);
                col[j] |= (1 << u);
                area[idx] |= (1 << u);
            }
        }
        return true;
    }


}
