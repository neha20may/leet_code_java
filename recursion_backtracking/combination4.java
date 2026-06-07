package recursion_backtracking;

import java.util.ArrayList;
import java.util.*;

public class combination4 {
    static class Solution {
        public int combinationSum4(int[] nums, int target) {
            List<Integer> buffer = new ArrayList();
            List<Integer> count = new ArrayList(1);
            count.add(0);
            Map<Integer, Integer> memo = new HashMap();

//            recurse(nums, target, buffer, 0, 0, count, 0);
            int[] mem = new int[target + 1];
            recurseCount(nums, target, mem);
            return count.get(0);
        }

        private void recurseCount(int[] nums, int target, int[] mem) {
            //base

            Arrays.fill(mem, 0);
            //mem[target]= mem[target-i..] i <-- nums
            for (int i = 0; i < nums.length; i++) {
                mem[nums[i]] = 1;
            }
            mem[0] = 0;
            for (int t = 1; t <= target; t++) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= t) {
                        mem[t] += mem[t - nums[i]];
                    }
                }
            }
            //3-1= 2 3-2 = 1 3-3 =0
            // 1* 1 1 | 1* 2 |  2* 1 | **
            //2-1=1 --> 1 2-2 =0 --> 1 == 1+ 1 --> 2  --> total 2 ways
            // 1 1
            // 2
            //1-1=0--1
            System.out.println(Arrays.toString(mem));

        }

        void recurse(int[] nums, int target, List<Integer> buffer, int bi, int start, List<Integer> count, int buffersum) {
            // System.out.println("start="+start+" bi ="+bi+ " buffer = "+ buffer +" bufferSum = "+buffersum);
            if (buffersum == target) {
                System.out.println("found ans " + buffer);
                count.add(0, count.get(0) + 1);
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (buffersum + nums[i] > target) {
                    continue;
                }
                buffer.add(nums[i]);
                recurse(nums, target, buffer, bi + 1, i, count, buffersum + nums[i]);
                buffer.remove(buffer.size() - 1);


            }

        }

        /**
         * 4
         * 4
         * 2 (11| 2)
         * 1 3- (1 (11|2) | 1 (2| 11) | 2( 11) | )
         *
         */
        static void main() {
            Solution s = new Solution();
            int[] a = {4, 2, 1};
            int target = 32;
            s.combinationSum4(a, target);
        }

    }
}
