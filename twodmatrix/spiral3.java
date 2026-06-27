package twodmatrix;

import java.util.*;

public class spiral3 {
    static class BadSolution {
        static public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
            int m = rows;
            int n = cols;
            int top = rStart;
            int bottom = rStart + m - 1;
            int left = cStart;
            int right = cStart + n - 1;
            int count = 0;
            List<int[]> result = new ArrayList();

            while (count < m * n) {
                System.out.println("left > right");
                for (int i = left; i <= right; i++) {
                    System.out.println("left > right"+" i = "+i+" top "+top);
                    if (top >= 0 && top <= m - 1 && i >= 0 && i <= n - 1) {
                        // result.add(matrix[top][i]);
                        result.add(new int[]{top, i});
                        count++;
                    }
                }
                top++;
                System.out.println("top > bottom");
                for (int i = top; i <= bottom; i++) {
                    if (right >= 0 && right <= n - 1 && i >= 0 && i <= m - 1) {
                        // result.add(matrix[i][right]);
                        result.add(new int[]{i, right});
                        count++;
                    }
                }
                right--;
                System.out.println("right > left");
                if (top <= bottom) {// ome row
                    for (int i = right; i >= left; i--) {
                        if (bottom >= 0 && bottom <= m - 1 && i >= 0 && i <= m - 1) {
                            // System.out.println("row ="+bottom+" col ="+i);
                            // result.add(matrix[bottom][i]);
                            result.add(new int[]{bottom, i});
                            count++;
                        }
                    }
                    bottom--;
                }
                System.out.println("bottom > top");
                if (left <= right) {//one column
                    for (int i = bottom; i >= top; i--) {
                        if (left >= 0 && left <= n - 1 && i >= 0 && i <= m - 1) {
                            // result.add(matrix[i][left]);
                            result.add(new int[]{i, left});
                            count++;
                        }
                    }
                    left++;
                }

            }
            return result.toArray(int[][]::new);

        }
    }
    static class Solution {
        static public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
            //L- 1 D- 1 R- 2 U- 2 | L-2 D -2 R -3 U -3 | and so on

            int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

            int lrcount = 1;
            int rucount = lrcount + 1;
            int count = 1;
            int[][] result = new int[rows*cols][2];
            result[0] = new int[] { rStart, cStart };
            int curRow = rStart;
            int curCol = cStart;
            while (count <= rows * cols) {
                System.out.println("ldStep "+lrcount +" ruStep "+rucount);
                //L
                for (int i = 0; i < lrcount; i++) {
                    int[] deltadir = dir[0];
                    curRow = curRow + deltadir[0];
                    curCol = curCol + deltadir[1];
                    int[] index = new int[] { curRow, curCol };
                    if (curRow >= 0 && curRow < rows && curCol >= 0 && curCol < cols) {
                        result[count++] = index;
                    }
                }
                //D
                for (int i = 0; i < lrcount; i++) {
                    int[] deltadir = dir[1];
                    curRow = curRow + deltadir[0];
                    curCol = curCol + deltadir[1];
                    int[] index = new int[] { curRow, curCol };
                    if (curRow >= 0 && curRow < rows && curCol >= 0 && curCol < cols) {
                        result[count++] = index;
                    }
                }
                //R
                for (int i = 0; i < rucount; i++) {
                    int[] deltadir = dir[2];
                    curRow = curRow + deltadir[0];
                    curCol = curCol + deltadir[1];
                    int[] index = new int[] { curRow, curCol };
                    if (curRow >= 0 && curRow < rows && curCol >= 0 && curCol < cols) {
                        result[count++] = index;
                    }
                }
                //U
                for (int i = 0; i < rucount; i++) {
                    int[] deltadir = dir[3];
                    curRow = curRow + deltadir[0];
                    curCol = curCol + deltadir[1];
                    int[] index = new int[] { curRow, curCol };
                    if (curRow >= 0 && curRow < rows && curCol >= 0 && curCol < cols) {
                        result[count++] = index;
                    }
                }
                lrcount++;
                rucount++;
            }
            return result;

        }
    }

    public static void main(String[] args) {

        int [][]res= Solution.spiralMatrixIII(1,4,0,0);
        System.out.println(res);

    }
}
