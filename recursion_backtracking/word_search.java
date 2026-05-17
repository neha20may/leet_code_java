package recursion_backtracking;

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 */
public class word_search {
    static class Solution {
        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();
            Boolean[] ans = new Boolean[1];
            ans[0] = false;
            int m = board.length;
            int n = board[0].length;
            boolean[][] path = new boolean[m][n];
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    call(board, words, 0, i, j, ans, path, m, n);
                }
            }
            System.out.println("ans= " + ans[0]);
            return ans[0];
        }

        private void call(char[][] board, char[] words, int wi, int i, int j, Boolean[] ans, boolean[][] path, int m, int n) {
            //base

            if (wi == words.length) {
                //all matched
                ans[0] = true;
            }


            if (i < 0 || i > m - 1) {
                return;
            }
            if (j < 0 || j > n - 1) {
                return;
            }


            if (wi > words.length - 1) {
                return;
            }
            char c = words[wi];
//            for (int k = i; k < m; k++) {
//                for (int l = j; l < n; l++) {
                    char bc = board[i][j];
                    if (c != bc || path[i][j] == true) {
                        return;
                    } else {
                        path[i][j] = true;
                        call(board, words, wi + 1, i, j + 1, ans, path, m, n);//right
                        call(board, words, wi + 1, i, j - 1, ans, path, m, n);//left
                        call(board, words, wi + 1, i - 1, j, ans, path, m, n); //up
                        call(board, words, wi + 1, i + 1, j, ans, path, m, n);//down


                        path[i][j] = false;
                    }
//                }
//            }

        }
    }

    static void main() {
        Solution s = new Solution();
//        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board = {{'a', 'b'}, {'c', 'd'}};
        s.exist(board, "bcd");

    }
}
