package binarysearch;

public class searchrotatedduplicates {
    static class Solution {
        static public boolean search(int[] nums, int target) {
            int start= findStart(nums);
            System.out.println("start "+start);
            int n= nums.length;
            int a1= bs(nums, 0, start, target);
            if(a1!= -1){
                return true;
            }
            int a2= bs(nums, start, n, target);
            if(a2!= -1){
                return true;
            }
            return false;

        }
        static int bs(int []a, int start, int end, int target){
            //duplicate flow
            int ans=-1;
            int low= start; int high = end;
            while(low < high){
                int mid= low + (high- low)/2;
                if(a[mid]< target){
                    low= mid + 1;
                }else if(a[mid]> target){
                    high= mid - 1;
                }else{
                    ans= mid;
                    high= mid-1;
                }
            }
            return ans;
        }
        static  int findStart(int [] nums){
            int low = 0;
            int high= nums.length - 1;
            while(low <= high){
                int mid= low + (high-low)/2;
                if(mid > 0 && nums[mid-1] > nums[mid]){
                    return mid;
                }
                if(mid + 1 < nums.length && nums[mid] > nums[mid + 1]){
                    return mid + 1;
                }
                if(nums[mid] > nums[high]){
                    low= mid+1;
                }else if(nums[mid] < nums[high]){
                    high= mid-1;
                }else {
                    if(high > 0 && nums[high - 1] > nums[high]){
                        return high;
                    }
                    high--;
                }
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        int [] nums={1, 0, 2, 3, 4};
        boolean r= Solution.search(nums, 2);
        System.out.println(r);
    }
}
