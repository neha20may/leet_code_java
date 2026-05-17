package recursion_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class coin_sum_repeat {
    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int [] buffer= new int[target];
            List<List<Integer>> ans= new ArrayList();
            call(candidates, buffer, ans, 0, 0, 0, target);
            System.out.println("ans= "+ans.toString());
            return ans;
        }
        void call(int[] candidates, int []buffer, List<List<Integer>> ans, int i, int bi, int bufferSum, int target){
            //base
            System.out.println("buffer sum = "+bufferSum + " buffer ="+Arrays.toString(buffer) +" i ="+i+ "bi = "+bi);
            if(bufferSum == target){
                ans.add(getBufferTillBi(buffer, bi));
                return;
            }
            if(bufferSum > target){
                return;
            }
            if(i > candidates.length){
                return;
            }

            //

            for(int j= i; j< candidates.length; j++){
                buffer[bi]=candidates[j];
                call(candidates, buffer, ans, j, bi+1, bufferSum + candidates[j], target);
                //how do i repeat the coin?
            }

        }

        private List<Integer> getBufferTillBi(int[] buffer, int bi) {
            List<Integer> a= new ArrayList();
            for(int i=0; i < bi;i++){
                a.add(buffer[i]);
            }
            return a;
        }
    }

    static void main() {
        Solution s = new Solution();
        s.combinationSum(new int[]{2,3,6,7}, 7);
    }

}
