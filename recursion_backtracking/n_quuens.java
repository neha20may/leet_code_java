package recursion_backtracking;

import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * <p>
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 9
 */
public class n_quuens {
    static class Solution {
        public int totalNQueens(int n) {

            int[][] board = new int[n][n];
            Set<Set<List<Integer>>> ans= new HashSet<>();
            call(board, n, 0, ans);

            System.out.println("found configurations");
            System.out.println(ans);
            return ans.size();
        }

        private void call(int[][] board, int n, int queenNo, Set<Set<List<Integer>>> ans) {

            print2darr(board);
            System.out.println("queen no to be placed " + (queenNo + 1));
            //base
            if (queenNo == n ) {
                //all quuens areplaced
                System.out.println("---- all queens placed -----");
                //distinct solution
                Set<List<Integer>> filledBoard= new HashSet<>();
                for(int i=0; i<n;i++){
                    for(int j=0; j<n; j++){
                        if(board[i][j]!=0){
                            filledBoard.add(Arrays.asList(i, j));
                        }
                    }
                }
                if (ans.contains(filledBoard)){
                    return;
                }else{
                    ans.add(filledBoard);
                }
                return;
            }
            //place this queen somewhere on board
            // find all plces where this queen can be placed and then iterate to place next
//            for (int i = 0; i < n; i++) { //we dont need rows else n=8/9 goes TLE
                for (int j = 0; j < n; j++) {
                    if (board[queenNo][j] == 0 && canPlace(board, queenNo, j)) {
                        board[queenNo][j] = queenNo + 1;
                        call(board, n, queenNo + 1, ans);
                        board[queenNo][j] = 0;
                    }
                }
//            }


        }

        private void print2darr(int[][] buffer) {
            int n = buffer.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(buffer[i][j] + "  ");
                }
                System.out.println();
            }
//            System.out.println();
        }

        private boolean canPlace(int[][] buffer, int i, int j) {
            int n = buffer.length;
            if (i < 0 || j < 0) {
                return false;
            }
            if (i >= n || j >= n) {
                return false;
            }
            //check row , column and diag if any other quuen is placed

            for (int col = 0; col < n; col++) {
                if (buffer[i][col] != 0) {
                    return false;
                }
            }

            for (int row = 0; row < n; row++) {
                if (buffer[row][j] != 0) {
                    return false;
                }
            }
            //diag?
            /**  0  1  2  3
             * 0 1  2  3  4
             * 1 5  6  7  8
             * 2 9  10 11 12
             * 3 13 14 15 16
             *
             * | Direction  | Row change | Col change |
             * | ---------- | ---------- | ---------- |
             * | up-left    | -1         | -1         |
             * | up-right   | -1         | +1         |
             * | down-left  | +1         | -1         |
             * | down-right | +1         | +1         |
             */
            int ii = i;
            int jj = j;
            while (ii > 0 & jj > 0) {
                if (buffer[ii - 1][jj - 1] == 0) {
                    ii = ii - 1;
                    jj = jj - 1;
                } else {
                    return false;
                }

            }
            ii = i;
            jj = j;
            while (ii < n - 1 & jj < n - 1) {
                if (buffer[ii + 1][jj + 1] == 0) {
                    ii = ii + 1;
                    jj = jj + 1;
                } else {
                    return false;
                }
            }

            //up right
//            up-right   | -1         | +1         |
            ii = i;
            jj = j;
            while (ii > 0 & jj < n - 1) {
                if (buffer[ii - 1][jj + 1] == 0) {
                    ii = ii - 1;
                    jj = jj + 1;
                } else {
                    return false;
                }
            }

//             * | down-left  | +1         | -1         |

            ii = i;
            jj = j;
            while (ii < n - 1 & jj > 0) {
                if (buffer[ii + 1][jj - 1] == 0) {
                    ii = ii + 1;
                    jj = jj - 1;
                } else {
                    return false;
                }
            }

            //actually downs are not required bcz you are placing left to right and top to bottom  - so up left and up right are needed
            return true;

        }
    }

    static void main() {
        Solution s = new Solution();
        s.totalNQueens(8);
    }
}
