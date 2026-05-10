package binarysearch;

import java.util.Arrays;

public class divisor {

    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = Arrays.stream(nums).max().getAsInt();
        int mid = low + (high - low) / 2;
        int ans = Integer.MAX_VALUE;
        while (low < high) {
            mid = low + (high - low) / 2;
            int divisor = mid;
            System.out.println("mid=" + mid);
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                int r = (int) Math.ceil(nums[i]*1.0 / divisor*1.0);
                sum = sum + r;

            }
            System.out.println("sum=" + sum);

            if (sum > threshold) {
                low = mid + 1;

            } else if (sum < threshold) {
//                ans = mid;
                if(ans > mid){
                    ans= mid;
                }
                high = mid - 1;
            } else {
//                ans = mid;
                if(ans > mid){
                    ans=mid;
                }
                high = mid - 1;
            }
            System.out.println("ans=" + ans);
        }
        return ans;


    }

    static void main() {

        divisor obj = new divisor();
        int []a={1,2,5,9};
        int threshold=6;
        System.out.println("final ans="+obj.smallestDivisor(a, threshold));
    }
}
