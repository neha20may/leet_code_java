package dp;

import java.util.*;

public class maxKcoinsNPiles {
    static class Solution {
        public int maxValueOfCoins(List<List<Integer>> piles, int k) {
            int[] index = new int[k];
            int n = piles.size();
            int[][][] memo = new int[n + 1][2001][k + 1];
            int res = rec(piles, n, k, index, memo, 0);
            return res;

        }

        int rec(List<List<Integer>> piles, int n, int k, int[] pileIndices, int[][][] memo, int start) {
            // System.out.println("n "+n +" k "+k+" pile indices "+Arrays.toString(pileIndices));

            if (k < 0) {
                return 0;
            }
            int maxprofit = 0;
            for (int i = start; i < n; i++) {

                System.out.println("n " + n + " k " + k + " pile indices " + Arrays.toString(pileIndices) + " i to pileIndices pile " + i);
                List<Integer> pile = piles.get(i);

                System.out.println("pile " + pile);

                int pi = pileIndices[i];
                System.out.println("pi " + pi);
                if (pi > pile.size() - 1) {
                    return 0;
                }

                if (memo[i][pi][k] != 0) {
                    maxprofit = Math.max(maxprofit, memo[i][pi][k]);
                    return maxprofit;
                }


                pileIndices[i] = pi + 1;
                int p1 = pile.get(i) + rec(piles, n, k - 1, pileIndices, memo, i+1);

                pileIndices[i] = pi;
                int p2 = rec(piles, n, k, pileIndices, memo, i+1);

                maxprofit = Math.max(maxprofit, Math.max(p1, p2));
                memo[i][pi][k] = maxprofit;
            }
            return maxprofit;
        }
    }

    static void main() {
        Solution s = new Solution();
        List<List<Integer>> piles = new ArrayList<>();
        piles.add(List.of(1, 100, 3));
        piles.add(List.of(7, 8, 9));
        s.maxValueOfCoins(piles, 2);

    }
}
