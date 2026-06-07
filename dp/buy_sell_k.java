package dp;

import java.util.*;

public class buy_sell_k {
    static class Solution {
        public int maxProfit(int k, int[] prices) {
            //every time when i write recurson first  I get tabulation idea!
            //uff this was without K!
            int m = prices.length;
            int[][][] dp = new int[2][m + 1][k+1];
//            dp[0][m] = -prices[m - 1];
//            dp[0][m] = -prices[m - 1];
//            dp[1][m] = prices[m - 1];
//            dp[1][m] = prices[m - 1];
            List<Integer> trail = new ArrayList();
            int k2 = m - k;
//            for (int j = m - 1; j >= 0; j--) {
//                // System.out.println(" j= "+j);
//                // System.out.println("dp[1][j+1] ="+ dp[1][j+1] + " dp[0][j+1] = "+dp[0]
//                dp[0][j] = Math.max(-prices[j] + dp[1][j + 1], dp[0][j + 1]);
//                dp[1][j] = Math.max(prices[j] + dp[0][j + 1], dp[1][j + 1]);
//
//            }
            for (int j = m - 1; j >= 0; j--) {
                for(int t=1; t<=k; t++){
                    dp[0][j][t] = Math.max(-prices[j] + dp[1][j + 1][t], dp[0][j + 1][t]); //no change in t
                    dp[1][j][t] = Math.max(prices[j] + dp[0][j + 1][t-1], dp[1][j + 1][t]); //one minor change t-1
                }

            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j <= m; j++) {
                    System.out.print(" " + dp[i][j]);
                }
                System.out.println();
            }
            return dp[0][0][k];
        }
    }

    static void main() {
        Solution s = new Solution();
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int k = 2;
        s.maxProfit(k, prices);
    }

}
