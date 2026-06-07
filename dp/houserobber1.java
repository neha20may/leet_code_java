package dp;

import java.util.Arrays;

public class houserobber1 {
    static class Solution {

        public int rob(int[] nums) {

            int n = nums.length;

// int maxA= Arrays.stream(nums).max().getAsInt();

            int sumA = Arrays.stream(nums).sum();

// System.out.println("sum A" + sumA);

            int[][] memo = new int[n + 2][40001];

            for (int[] row : memo) {

                Arrays.fill(row, -1);

            }


            int[] memo2 = new int[n + 1];

// return rob2(0, n, nums, 0, memo);

            Arrays.fill(memo2, -1);

            return rob3(0, n, nums, memo2);

// return memo2[0];

        }


        int rob3(int end, int n, int[] nums, int[] memo) {

// System.out.println("start "+start +" amount "+amount+" memo "+Arrays.toString(memo));

            if (end >= n) {

// memo[end]= 0; //this is RCA

// System.out.println("start "+start +" amount "+amount+" memo "+Arrays.toString(memo)+" return rob amount "+amount);

                return 0;

            }

            if (memo[end] != -1)

                return memo[end];


            int rob1 = rob3(end + 1, n, nums, memo);

            int rob2 = nums[end] + rob3(end + 2, n, nums, memo);

            int rob = Math.max(rob1, rob2);


            memo[end] = rob;

// System.out.println("start "+start +" amount "+amount+" memo "+Arrays.toString(memo)+" return rob"+rob);

            return rob;

        }

        int rob2(int start, int n, int[] nums, int amount, int[][] memo) {

// System.out.println("start "+start +" amount "+amount+" memo "+Arrays.toString(memo));

            if (start >= n) {

                memo[start][amount] = amount;

// System.out.println("start "+start +" amount "+amount+" memo "+Arrays.toString(memo)+" return rob amount "+amount);

                return amount;

            }

            if (memo[start][amount] != -1)

                return memo[start][amount];

            int rob1 = rob2(start + 1, n, nums, amount, memo);

            int rob2 = rob2(start + 2, n, nums, amount + nums[start], memo);

            int rob = Math.max(rob1, rob2);

            memo[start][amount] = rob;

// System.out.println("start "+start +" amount "+amount+" memo "+Arrays.toString(memo)+" return rob"+rob);

            return rob;

        }

    }

    static void main() {
        Solution s = new Solution();
//        int []nums={114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int ans = s.rob(nums);
        System.out.println("ans= " + ans);
    }

}
