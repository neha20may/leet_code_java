import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SumTwoNumbers {
    //    Level: Easy
//    Given an array of integers, find a pair of integers that sums to a number Target.
//    For e.g, if A = [6,3,5,2,1,7]. Target = 4, Result= [3,1]
    public static void main(String[] args) {
        Integer[] A = {6, 3, 5, 2, 1, 7};
        int target = 6;
        /**
         * Approach 0: choose first number then iterate rest of the array ; Complexity = N^2
         * Approach 1: Sort the array and two pointers = O(N log N); start from i=0; j= N-1 and move one at a time.
         * Approach 2: Hash set and then choose first number and {T - Y} ; Find it but then what is that X? if you iterate then y = T-x; complexity O(N)
         */
        Pair<Integer, Integer> result = method_1(A, target);
        System.out.println(result);
        result = method_2(A, target);
        System.out.println(result);

    }

    private static Pair<Integer, Integer> method_1(Integer[] a, int target) {
//        Collections.sort(List.of(a)); List.of() creates immutable list and collections might or might not work !
        Collections.sort(Arrays.asList(a));
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int x = a[i];
            int y = a[j];
            if (x + y == target) {
                return new Pair<Integer, Integer>(x, y);
            } else if (x + y < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    private static Pair<Integer, Integer> method_2(Integer[] a, int target) {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(a));
        int i = 0;
        while (i < a.length - 1) {
            int x = a[i];
            Boolean res = set.contains(target - x);
            if (res) {
                return new Pair<Integer, Integer>(x, target - x);
            } else {
                i++;

            }
        }
        return null;
    }
}
