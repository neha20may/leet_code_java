package dp;

import java.util.*;

public class min_falling_path_sum {
    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int m= matrix.length;
            int n= matrix[0].length;
            int [][] memo= new int[m][n];
            for(int [] r: memo){
                Arrays.fill(r, 100001);
            }

            int rowMin= Integer.MAX_VALUE;



            for(int j=0; j<n;j++){
                //j=0
                rec(m, n,  0, j, memo, matrix);
                rowMin= Math.min(rowMin, memo[0][j]);

            }
            print2D(memo, m, n);
            // int colMin= Integer.MAX_VALUE;
            // for(int j=0; j<n;j++){
            //     //i=0
            //     colMin= Math.min(colMin, memo[0][j]);
            // }
            return rowMin;

        }
        void print2D(int [][]mat, int m, int n){
            for(int [] r: mat){
                System.out.println(Arrays.toString(r));
            }
        }
        int rec(int m, int n,  int i, int j, int [][]memo, int [][]grid){
            System.out.println(" start i= "+i +" j= "+j);
            if(i>=m){
                return 0;
            }
            if((j<0 || j>=n) && i<m){
                return 100001;
            }

            // if(i== m-1 || j== n-1){
            //     memo[i][j]= grid[i][j];
            //     return grid[i][j];
            // }
//            if(memo[i][j]!=100001){
//                return memo[i][j];
//            }
            int c1= grid[i][j]+ rec(m, n, i+1, j, memo, grid);
            int c2= grid[i][j]+ rec(m, n, i+1, j+1, memo, grid);
            int c3= grid[i][j]+ rec(m, n, i+1, j-1, memo, grid);
            int finalAns= Math.min(c3, Math.min(c1, c2));
            System.out.println(" end i= "+i +" j= "+j +" min found "+finalAns);
//            memo[i][j]= finalAns;
            return finalAns;
        }
    }

    public static void main(String[] args) {
        Solution s= new Solution();
//        [[2,1,3],[6,5,4],[7,8,9]]
        int [][] mat={{2,1,3},{6,5,4},{7,8,9}};
        s.minFallingPathSum(mat);

    }
}
