package array;

import java.util.*;

public class trapping_rain_water {
    static class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int[] ht = new int[n + 2];
            ht[0] = Integer.MIN_VALUE;
            ht[n + 1] = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                ht[i] = height[i - 1];
            }
            int total = 0;
            for (int i = 1; i <= n; i++) {
                //for this i find left b and right b;
                int lb = i;
                while (ht[lb]<= ht[lb-1]) {
                    lb--;
                }

                int rb = i;
                while (ht[rb]<= ht[rb+1]) {
                    rb++;
                }

                System.out.println("for i "+i+" lb=" + lb + " right b " + rb);
                //fill now;
                //these lb adn right b >= i;
                int finalHt = Math.min(ht[lb], ht[rb]);
                int j = lb;
                int waterdelta=0;
                while (j <= rb) {
                    int diff = finalHt - ht[j];
                    if(diff>0){
                        waterdelta += diff;
                        ht[j] += diff;
                    }

                    j++;
                }
                System.out.println("i= "+i+" water delta "+waterdelta);
                total+=waterdelta;

                //at the end here we haev lb; at the end it could be 0; which is -inf;
            }
            return total;
        }

        public int trap2(int[] height) {
            int water = 0;
            int counter = 1;
            int deltaWater = 0;
            do {
                deltaWater = 0;
                System.out.println("counter = " + counter + " ht = " + Arrays.toString(height));
//                int[] valleys = finavalleys(height);
                int[] valleys = height;
                System.out.println("valleys =" + Arrays.toString(valleys));

                for (int val : valleys) {
                    if (val != -1) {
                        int w = fillwater(height, val);
                        System.out.println(" valley point " + val + " water filled " + w);
                        deltaWater += w;
                    }

                }
                water += deltaWater;


            } while (deltaWater != 0);
            return water + deltaWater;
        }

        int fillwater(int[] ht, int val) {
            int n = ht.length;

            int leftB = 0;
            for (int i = val - 1; i >= 0; i--) {
                if (ht[i] >= ht[i + 1]) {
                    leftB = i;
                } else {
                    break;
                }
            }
            int rightB = n - 1;
            for (int i = val + 1; i < n; i++) {
                if (ht[i] >= ht[i - 1]) {
                    rightB = i;
                } else {
                    break;
                }
            }
            System.out.println("val point " + val + " left b " + leftB + " rightB " + rightB);
            int minHt = Math.min(ht[leftB], ht[rightB]);
            int w = 0;
            int bi = leftB;
            while (bi <= rightB) {
                if (ht[bi] <= minHt) {
                    w += minHt - ht[bi];
                    ht[bi] += minHt - ht[bi];
                } else {
                    w += 0;
                }
                bi++;
            }
            return w;
        }

        int[] finavalleys(int[] a) {

            int vi = 0;
            int n = a.length;
            int[] va = new int[n];
            Arrays.fill(va, -1);
            int[] b = new int[n + 2];
            int counter = 1;
            for (int x : a) {
                b[counter++] = x;
            }
            b[0] = Integer.MIN_VALUE;
            b[n + 1] = Integer.MIN_VALUE;

            for (int i = 1; i <= n; i++) {
                Boolean left = b[i - 1] >= b[i];
                Boolean right = b[i] <= b[i + 1];
                if (left && right) {
                    va[vi++] = i - 1;
                }
            }
            return va;
        }
    }

    public static void main(String[] args) {
        int[] ht = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int []ht2= new int[20000];
        for(int i=0; i<20000; i++){
            ht2[i]=1;
        }
        Solution solution = new Solution();
        int ans = solution.trap(ht2);
        System.out.println(ans);
    }
}
