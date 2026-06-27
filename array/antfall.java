package array;

import java.util.*;

public class antfall {
    static class Solution {
        static public int getLastMoment(int n, int[] left, int[] right) {
            int l = left.length;
            int r = right.length;
            Arrays.sort(left);
            Arrays.sort(right);
            System.out.println(Arrays.toString(left));
            System.out.println(Arrays.toString(right));
            int ans = 0;
            // if(l>r){
            //     ans = findmax(left);

            // }else if(r>l){
            //     ans= n-findmin(right);
            // }else{
            int a2 = findmax(left);
            int a1 = findmin(right);
            System.out.println("a1 " + a1 + " a2 " + a2);
            if (a2 < a1) {
                // nevre meet
                // immediately turn

                int n1 = a1;
                int n2 = n - a2;
                System.out.println("never meet :: n1 " + n1 + " n2 " + n2);
                ans = Math.max(n1, n2);
                return ans;
            }
            if (a1 == (a2 - 1)) {
                // nevre meet
                // immediately turn

                int n1 = n - a1;
                int n2 = a2;
                System.out.println("never meet :: n1 " + n1 + " n2 " + n2);
                ans = Math.max(n1, n2);
                return ans;
            }
            int meet = Math.abs(a1 - a2) / 2;
            System.out.println("meet =" + meet);
            int n1 = meet + meet + a1;
            System.out.println("a1 fall =" + n1);
            int n2 = n - (a2 - meet) + meet;
            System.out.println("a2 fall =" + n2);
            ans = Math.max(n1, n2);
            // }
            return ans;
        }

        static int findmax(int[] a) {
            return Arrays.stream(a).max().getAsInt();
        }

        static int findmin(int[] a) {
            return Arrays.stream(a).min().getAsInt();
        }
    }

    public static void main(String[] args) {
        int n=20;
        int []left={9,3,13,10};
        int [] right={4,7,15};
        int ans= Solution.getLastMoment(n, left, right);
        System.out.println("ans "+ans);

    }

}
