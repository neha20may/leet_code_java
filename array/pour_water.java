package array;

import java.util.Arrays;

public class pour_water {
    static class Solution {
        public int[] pourWater(int[] heights, int volume, int k) {
            //for this k - find the left most k1
            //for this k - find the right most k2
            //(k1-k)*1 = this much can be filled here
            // (k2-k-1)*1 = this muh can be filled in right
            //rest stays on the top
            //edge cases:
            //all can be filled in the left ;
            //partial can be filled in left
            //same for right
            //each placei n k1-k takes up the ht(k) so iteratovely push it
            int[] ans = new int[heights.length];
            int i = 0;
            for (int x : heights) {
                ans[i++] = x;
            }
            int counter=0;
            while (volume > 0) {
                System.out.println("counter. "+counter +" ans array "+Arrays.toString(ans)+" volume "+volume);
                int k1 = findleft(ans, k);
                int k2 = findright(ans, k);
                System.out.println("left greater pillor " + k1);
                System.out.println("right greater pillor " + k2);

                if (k1 < k) {
                    //you can fill left
                    int leftvolume = k - k1 - 1;
                    System.out.println("left possible volumne =" + leftvolume + " total volume " + volume);
                    if (leftvolume <= volume) {
                        //all goes to left
                        fillleft(heights, k1, k, leftvolume, ans);
                        volume -= leftvolume;
                    } else {

                        fillleft(heights, k1, k, volume, ans);
                        volume = 0;
                    }

                }
                if (volume > 0) {

                    //either k1 bigger or nothing left to fill
                    //try right
                    int rightvolume = k2 - k - 1;
                    System.out.println("right possible volumne =" + rightvolume + " total volume " + volume);
                    if (rightvolume <= volume) {
                        fillright(heights, k, k2, rightvolume, ans);
                        volume -= rightvolume;
                    } else {
                        fillright(heights, k, k2, volume, ans);
                        volume = 0;
                    }
                }
                //left and right filled
                //keep rest at k
                if (volume > 0) {
                    ans[k] = ans[k] + 1;
                    volume = volume - 1;
                }

                counter++;
            }

            return ans;

        }

        void fillleft(int[] heights, int k1, int k, int leftvolume, int[] ans) {
            int next = k;
            for (int i = k - 1; i > k1; i--) {
                //fill this poistion till it reaches next
                int diff = ans[i + 1] - ans[i];
                int temp = leftvolume;
                leftvolume -= diff;
                if (leftvolume < 0) {
                    ans[i] += temp;
                    break;
                } else {
                    ans[i] += diff;
                }

            }

        }

        void fillright(int[] heights, int k, int k2, int rightvolume, int[] ans) {
            int prev = k;
            int n = heights.length;
            for (int i = k + 1; i < k2; i++) {
                int diff = ans[i - 1] - ans[i];
                int temp = rightvolume;
                rightvolume -= diff;
                if (rightvolume < 0) {
                    ans[i] += rightvolume;
                    break;
                } else {
                    ans[i] += diff;
                }

            }


        }

        int findleft(int[] heights, int k) {
            int ans = -1;
            //till you hit greter than
            for (int i = k - 1; i >= 0; i--) {
                if (heights[i] >= heights[k]) {
                    ans = i;
                    break;
                }
            }
            return ans;
        }

        int findright(int[] heights, int k) {

            int n = heights.length;
            int ans = n;
            //till you hit greter than
            for (int i = k + 1; i < n; i++) {
                if (heights[i] >= heights[k]) {
                    ans = i;
                    break;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ht = {1,2,3,4,3,2,1,2,3,4,3,2,1};
        int v = 10;
        int k = 2;
        int[] ans = solution.pourWater(ht, v, k);
        System.out.println(Arrays.toString(ans));
    }
}
