package binarysearch;

public class InsertSearch {
    static class Solution {
        static public int searchInsert(int[] nums, int target) {

            int low=0;
            int high= nums.length-1;
            int result= -1;
            while(low<=high){
                int mid = low + (high-low)/2;
                if(nums[mid]> target){
                    high = mid-1;
                }else if(nums[mid]<target){
                    low= mid+1;
                }else{
                    result = mid;
                    break;
                }
            }
            if(result == -1){
                result = low;
            }
            return result;

        }
    }

    public static void main(String[] args) {
        int [] num={1,2,4,4,5,6,8};
        int result= Solution.searchInsert(num, 4);
        System.out.println(result);
    }

}
