package dp;

import com.sun.security.jgss.GSSUtil;

import java.util.*;

/**
 * one question that each time i take time
 */
public class longestincreasingsubsequence {
    static class Solution {
        public int lengthOfLIS(int[] nums) {
            List<Integer> buffer= new ArrayList();
            return rec(nums, 0, buffer);

        }
        int rec(int [] nums, int i, List<Integer> buffer){
            //base
            System.out.println("i ="+i+" buffer "+buffer);
            if(i >= nums.length){
                return buffer.size();
            }
            int a=0; int b=0;
            if(buffer.isEmpty() ||!buffer.isEmpty() && buffer.get(buffer.size()-1) < nums[i] ){
                buffer.add(nums[i]);
                a= rec(nums, i+1, buffer);
                buffer.remove(buffer.size()-1);
            }
            b= rec(nums, i+1, buffer);
            return Math.max(a,b);

        }
    }

    static void main() {
        Solution s= new Solution();
        int[] nums={10,9,2,5,3,7,101,18};
        int res = s.lengthOfLIS(nums);
        System.out.println("res= "+res);
    }

}
