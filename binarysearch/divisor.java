package  binarysearch;
import java.util.Arrays;

public class divisor {

    public int smallestDivisor(int[] nums, int threshold) {
        int low = Arrays.stream(nums).min().getAsInt();
        int high = Arrays.stream(nums).max().getAsInt();
        int mid = low + (high - low) / 2;
        int ans = -1;
        while (low < high) {
            mid = low + (high - low) / 2;
            int divisor = mid;
            System.out.println("mid=" + mid);
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                int r = nums[i] / divisor;
                sum = sum + r;

            }
            System.out.println("sum=" + sum);

            if (sum > threshold) {
                low = mid + 1;

            } else if (sum < threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
            System.out.println("ans" + ans);
        }
        return ans;


    }

    static void main() {
        System.out.println("hi");
    }
}
